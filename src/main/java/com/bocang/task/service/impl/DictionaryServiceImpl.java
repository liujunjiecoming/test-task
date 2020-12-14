package com.bocang.task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bocang.task.dao.DictionaryDao;
import com.bocang.task.entity.DictionaryDO;
import com.bocang.task.service.DictionaryService;
import org.springframework.stereotype.Service;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 上午9:34 20-12-10
 */

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, DictionaryDO> implements DictionaryService {
}
