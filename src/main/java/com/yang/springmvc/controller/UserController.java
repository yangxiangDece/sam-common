package com.yang.springmvc.controller;

import com.yang.springmvc.UserParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//@SessionAttributes(value = {"user"},types = String.class) //这里会将键为user的值放到session中，或者将键为String.class的值放到session中
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 视图解析器
     * 返回值通过视图解析器为实际的物理视图，对于InternalResourceViewResolver视图解析器，会做如下解析：
     *      通过prefix + returnVal + 后缀 这样的方式得到实际的物理视图，然后做转发，
     *      /WEB-INF/views/success.jsp
     * 原理：
     *    对于返回的类型，不管是Spring、View、ModelMap等，SpringMVC都会在内部将它们装配一个ModelAndView对象
     *
     * JSR 303验证
     *  1、加入hibernate validator 验证框架
     *  2、需要在bean的属性上添加对应的注解
     *  3、在目标方法bean类型额前面添加@Valid注解
     *  4、验证出错的信息都会放在BindingResult中
     *
     * @return
     */
    @RequestMapping("/helloworld")
    public String hello(UserParam userParam, BindingResult result){

        if(result.getErrorCount() > 0){
            System.out.println("参数出错了");
            for (FieldError error:result.getFieldErrors()) {
                System.out.println(error.getField()+"--"+error.getDefaultMessage());
            }
        }
        return "success";
    }

    /**
     * @ResponseBody 内部原理：
     *      HttpMessageConverter<ProviderConsumer> 是Spring3.0新添加的一个接口，负责将请求信息转换为一个对象（类型为T），将对象（类型为T）输出为响应信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/testJson")
    public List<Object> testJson(){
        List<Object> list=new ArrayList<>(2);
        list.add("a");
        list.add("b");
        return list;
    }

    /**
     * HiddenHttpMethodFilter：浏览器form表单只支持GET、POST请求，而DELETE、PUT等method并不支持，Spring3.0添加了一个过滤器，
     * 可以将这些请求方法转换为标准的http方法，使得支持GET、POST、PUT、DELETE请求
     *
     * 如何发送PUT请求和DELETE请求？
     *  1、需要在web.xml中配置HiddenHttpMethodFilter过滤器
     *  2、发送POST请求
     *  3、在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getTest/{id}",method = RequestMethod.GET)
    public String getTest(@PathVariable("id") Long id){
        System.out.println("getTest:"+id);
        return "success";
    }

    @RequestMapping(value = "/postTest",method = RequestMethod.POST)
    public String postTest(@RequestParam("id") Long id){
        System.out.println("postTest:"+id);
        return "success";
    }

    @RequestMapping(value = "/putTest/{id}",method = RequestMethod.PUT)
    public String putTest(@PathVariable("id") Long id){
        System.out.println("putTest:"+id);
        return "success";
    }

    @RequestMapping(value = "/deleteTest/{id}",method = RequestMethod.DELETE)
    public String deleteTest(@PathVariable("id") Long id){
        System.out.println("deleteTest:"+id);
        return "success";
    }

    /**
     * 请求头@RequestHeader
     * @param al
     * @return
     */
    @RequestMapping(value = "/testRequestHeader",method = RequestMethod.GET)
    public String testRequestHeader(@RequestHeader("Accept-Language") String al){
        System.out.println("testRequestHeader:"+al);
        return "success";
    }
    /**
     * cookie:@CookieValue 传cookie的名字
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/testCookieValue",method = RequestMethod.GET)
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId){
        System.out.println("testCookieValue:"+sessionId);
        return "success";
    }

    /**
     * 可以使用Servlet原生的API作为目标的方法参数
     * @param request
     * @param response
     * @param session
     * @param principal
     * @param inputStream
     * @param outputStream
     * @param reader
     * @param writer
     * @return
     */
    @RequestMapping(value = "/testServletApi",method = RequestMethod.GET)
    public void testServletApi(HttpServletRequest request, HttpServletResponse response,
                                 HttpSession session, Principal principal,
                                 InputStream inputStream,
                                 OutputStream outputStream,
                                 Reader reader,
                                 Writer writer) throws IOException {
        System.out.println("testServletApi:");
        writer.write("hello world ..testServletApi");
    }

    /**
     * Spring MVC提供了以下几种途径输出模型数据：
     *  1、ModelAndView：处理方法返回值类型为ModelAndView时，方法体即可通过该对象添加 模型数据
     *  2、Map及Model：入参为Model、ModelMap或Map时，处理方法结束以后，Map中的数据会自动添加到模型中
     *  3、@SessionAttributes：将模型中的某个属性暂存到HttpSession中，以便多个请求之间共享这个属性
     *  4、ModelAttributes：方法入参标注注解以后，入参的对象会放到数据模型中
     *
     * @return
     */
    @RequestMapping(value = "/testModelView",method = RequestMethod.GET)
    public String testModelView(Map<String,Object> map){
        map.put("names", Arrays.asList("Tom","Jerry","Mike"));
        System.out.println("testModelView:");
        return "success";
    }

    /**
     * 有 @ModelAttribute 标记的方法，会在每个目标方法执行之前被Spring MVC调用！
     *
     * Spring MVC 确定目标方法POJO类型入参的过程：
     *  1、确定一个key：
     *      1).若目标方法的POJO类型的参数没有使用 @ModelAttribute 作为修饰，则key为POJO类名的第一个字母小写
     *      2).若使用了 @ModelAttribute 来修饰，则key为 @ModelAttribute 注解的 value 属性值
     *  2、在implicitModel 中查找 key 对应的对象，若存在，则作为入参传入；若在 @ModelAttribute 标记的方法中在map中保存过，且key和上面的key一致，则可以获取到
     *  3、若implicitModel 中不存在key对应的对象，则检查当前的Handler是否使用了 @SessionAttributes 注解修饰，若使用了该注解，
     *      且 @SessionAttribute 注解的value 属性值包含了key，则会从HTTPSession 中获取key所对应的value值，若存在则直接传入到目标方法的入参中，若不存在则抛出异常
     *  4、若Handler 没有表示 @SessionAttribute 注解或 @SessionAttribute 注解的value值不包含key，则会通过反射来创建POJO类型的参数，传入为目标方法的参数
     *  5、SpringMVC 会把key 和 POJO类型的对象 保存到implicitModel 中，进而保存到request中
     *
     * @param map
     */
    @ModelAttribute
    public void testModelAttribute(Map<String,Object> map){
        System.out.println("testModelAttribute invoke...");
        map.put("token","token value");
    }

}
