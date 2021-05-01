package org.jzz.spbootDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试ModelAttribute
 */
@Controller
@RequestMapping("/modattr3")
public class ModelAttrController {
	
	Logger logger = LoggerFactory.getLogger(ModelAttrController.class);
	
    @ModelAttribute
    public void myModel1(Model model) {
    	model.addAttribute("mod1", "this modattr auto set by myModel1");
    	logger.debug("myModel1自动执行了...");
    }
	
	/**
     * 这个相当于 model.addAttribute("userName", name);
     */
//    @ModelAttribute("mod2")
//    public String myModel2(@RequestParam(value = "parm", required = false) String parm){
//      logger.debug("myModel2自动执行了...他获取输入参数：" + parm);
//      return parm;
//    }
    //也可以直接注释在返回值上，是一样的效果
    public @ModelAttribute("mod2") String myModel2(@RequestParam(value = "parm", required = false) String parm){
      logger.debug("myModel2自动执行了...他获取输入参数：" + parm);
      return parm;
    }
    
    /**
    * 这个相当于 model.addAttribute("string", name);
    * 因为你自己没有设置model的key值，所以它会默认value的类型第一个字母小写作为key值
    * 如果你是User对象，那它会默认key值为"user",这个在实际开发中并不适用
    * 因为太局限了，我们很难接受 key 为 string、int、user 等等这样的。
    */
    @ModelAttribute
    public String myModel3(@RequestParam(required = false) String parm) {
       logger.debug("myModel3自动执行了...获取了输入参数 ：" + parm + " 并设置了key为string...");
       return parm;
    }

    
    /**
     *  test1方法中，有两个值是通过Model1方法中放入的
     *  有两个值是通过前端获取的我们看后台打印结果
     */ 
    @RequestMapping(value="/test1")
    public @ResponseBody String test1(
            @ModelAttribute("mod1") String mod1,
            @ModelAttribute("mod2") String mod2,
            @ModelAttribute("string") String autoMod,
            @RequestParam("parm1") String parm1,
            @RequestParam("parm2") String parm2) {
        
    	String result = "mod1 = " + mod1 
    			+ "<br /> mod2 = " + mod2
    			+ "<br /> autoMod = " + autoMod
    			+ "<br /> parm1 = " + parm1
    			+ "<br /> parm2 = " + parm2;
    	logger.info("test1: " + result);
        return result;
    } 
    
    @RequestMapping(value="/index2")
    public String test2() {
    	
        return "index2";
    } 
    

}
