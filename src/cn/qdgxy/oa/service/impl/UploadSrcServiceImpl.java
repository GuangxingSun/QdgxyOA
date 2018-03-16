package cn.qdgxy.oa.service.impl;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.UploadSrc;
import cn.qdgxy.oa.service.UploadSrcService;

@Service
public class UploadSrcServiceImpl extends DaoSupportImpl<UploadSrc> implements UploadSrcService {

	public UploadSrc findUl() {
		
		return (UploadSrc) getSession().createQuery("FROM UploadSrc").uniqueResult();
	
	}

}