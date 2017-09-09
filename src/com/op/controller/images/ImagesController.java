package com.op.controller.images;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.op.controller.BaseController;
import com.op.service.images.ImagesService;

@Controller
@RequestMapping("/images")
public class ImagesController extends BaseController {
	@Resource(name="imagesServiceImpl")
	private ImagesService imagesServiceImpl;
	
}
