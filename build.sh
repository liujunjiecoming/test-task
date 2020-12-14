#!/bin/bash

#打包完后的jar名称，替换成你自己项目的名称，该名称可以在maven项目的pom中配置
proc="task"
#项目源码的目录地址（初始可能需要自己从Git拉下来）
SOURCE_HOME="/usr/local/publicwx/publicWechat"
#日志地址
APP_LOG="$SOURCE_HOME/target/catalina.base_IS_UNDEFINED/logs/log_info.log"
#环境配置 用户配置开发(dev)，测试(test)，生产(prod)的配置文件，避免频繁改动
PROFILES_ACTIVE="spring.profiles.active=dev"

#JVM启动参数，关于JVM调优这里不介绍，感兴趣的可以自行百度 JVM调优
JAVA_OPTS="-server -Xms512M -Xmx512M -Xss256k -Xmn256m -XX:SurvivorRatio=4 -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:MetaspaceSize=128M -XX:MaxMetaspaceSize=256M  -XX:CMSInitiatingOccupancyFraction=90 -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+DisableExplicitGC -XX:MaxTenuringThreshold=0 -XX:CMSFullGCsBeforeCompaction=100 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -Djava.awt.headless=true"

psid=0

#检查进程是否存在
checkpid() {
   javaps=$(pgrep -f "$proc")
   if [ -n "$javaps" ]; then
      psid=$javaps
   else
      psid=0
   fi
}


#编写启动方法
start() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "warn: $proc already started! (pid=$psid)"
      echo "================================"
   else
      echo "Starting $proc ..."
	  #到项目源码目录
      cd $SOURCE_HOME
	  #输出，准备获取最新代码
      echo -n "git pull source ,please wait ....."
	  #获取最新代码，此列只在目录所在分支pull
      #若想部署指定分支代码，可以在脚本调用参数中添加一个变量，用git checkout ${targer_branch}
      git pull

      #输出，最新代码已拉取完毕，准备打包
      echo -n "mvn package source ,please wait ....."

      #maven打包命令，此处特别注意是 —U ,是指引用快照版本的jar（引用自己的项目）每次都更新最新的。
      mvn clean package -Dmaven.test.skip=true
      #打包成功后默认是在启动项目的target目录下。
      cd target
      #输出，准备启动
      echo -n $"Starting $proc:"

      #循环加载所需的jar，此处和2的pom配置有关
      for name in *.jar
      do
        APP_CLASS="$name"
      done

      #启动脚本，--spring.profiles.active=   用于设置环境所使用的配置文件
      JAVA_CMD="java "$JAVA_OPTS" -jar "$APP_CLASS" --"$PROFILES_ACTIVE" &"
      #后台运行

      $JAVA_CMD &
      sleep 1
      checkpid
      if [ $psid -ne 0 ]; then
    	 echo "======================================"
         echo "$proc Start Success! (pid=$psid)[OK]"
         echo "======================================"
      else
         echo "[Failed]"
      fi
   fi

}


#查看日志
showlog() {
   tail -f $APP_LOG
}



#停用项目
stop() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo -n "Stopping $proc ...(pid=$psid) "
      kill -9 $psid

      if [ $? -eq 0 ]; then
         echo "[OK]"
      else
         echo "[Failed]"
      fi

      checkpid
      if [ $psid -ne 0 ]; then
         stop
      fi
   else
      echo "================================"
      echo "warn: $proc is not running"
      echo "================================"
   fi
}


#项目状态
status() {
   checkpid
   if [ $psid -ne 0 ];  then
      echo "$proc is running! (pid=$psid)"
   else
      echo "$proc is not running"
   fi
}


#设置脚本参数，启动的时候可以采用./脚本名称.sh start/stop/restart/log/status等参数
case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  log)
    showlog
    ;;
  status)
    status
    ;;
  restart)
    stop
    start
    ;;
esac

















































#echo '自动部署Springboot项目脚本...'
#echo '1. 拉取github代码...'
##git pull
#echo '2. 检查8010端口是否被占用...'
#pid_blog=`lsof -i :8010|grep -v "PID"|awk '{print $2}'`
#if [ "$pid_blog" != "" ];
#then
#    echo '8010端口被占用'
#    echo $pid_blog
#    kill -9 "$pid_blog"
#    echo $pid_blog '进程已被杀死'
#else
#    echo "端口未被占用"
#fi
#echo '3. 清理原有项目...'
#mvn clean
#echo '4. 打包...'
#mvn install
#echo '5. 后台运行jar包...'
#nohup java -jar target/task-1.0.jar > target/blog.out &