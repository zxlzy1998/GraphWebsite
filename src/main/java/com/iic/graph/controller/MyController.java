package com.iic.graph.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iic.graph.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Controller:创建处理器对象，对象放在springmvc容器中
 */
@Controller
public class MyController {

    /**
     * 逐个接收请求参数：
     *  要求：处理器方法的形参名和请求中参数名必须一致。
     *  同名的请求参数赋值给同名的形参
     */
    @RequestMapping(value = "/receiveproperty.do")
    public ModelAndView doRecive(String name, int age){
        //处理receiveproperty.do的请求。
        ModelAndView mv = new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        //指定视图(未配置视图解析器)
//        mv.setViewName("/WEB-INF/view/show.jsp");

        //配置视图解析器
        mv.setViewName("show");

        return mv;
    }
    /*
        处理用户提交的请求，springmvc中使用方法来处理；
        方法是自定义的，可以有多种返回值，多种参数
     */

    /**
     * 准备使用doSome来处理some.do的请求
     *   @RequestMapping：请求映射，把一个请求和一个方法绑定在一起
     *   一个请求指定一个方法处理
     *   属性：
     *      1. value 是一个String，表示请求的uri地址（some.do）。value是唯一的。
     *          在使用时推荐以“/”开头。
     *   说明：使用  @RequestMapping修饰的方法叫做处理器方法或者控制器方法。
     * @return ModelAndView
     *  model：数据，请求处理完成后，要显示给用户的数据
     *  view：视图，比如jsp等等
     */
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(){
        //处理some.do的请求。
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是doSome方法");

        //指定视图(未配置视图解析器)
//        mv.setViewName("/WEB-INF/view/show.jsp");

        //配置视图解析器
        mv.setViewName("show");

        return mv;
    }

    @RequestMapping(value = "/receiveobject.do")
    public ModelAndView receiveObject(Student myStudent){
        //处理some.do的请求。
        ModelAndView mv = new ModelAndView();
        mv.addObject("myname",myStudent.getName());
        mv.addObject("myage",myStudent.getAge());
        mv.addObject("myStudent",myStudent.toString());


        mv.setViewName("show");

        return mv;
    }

    //处理器方法返回void,相应ajax
    @RequestMapping(value = "returnVoid.do")
    public void doreceiveVoid(HttpServletResponse response, String  name, Integer age) throws IOException {
       //处理ajax请求，使用json做数据格式
        //service调用完成了，使用Student表示处理结果
        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        String json="";
        if(student !=null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(student);
            System.out.println("student转换的json"+json);
        }

        //输出数据，相应ajax请求
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }
}
