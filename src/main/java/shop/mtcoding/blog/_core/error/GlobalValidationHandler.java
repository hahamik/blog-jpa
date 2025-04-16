package shop.mtcoding.blog._core.error;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.mtcoding.blog._core.error.ex.Exception400;

import java.util.List;


// AOP 에서 기억해야 할 3가지 : 관점 - ASPECT, PointCut, 공통모듈 정의 -  Advice
@Component// ioc에 뜸
@Aspect // @Aspect를 붙이면 proxy가 됨 , 관점 관리
public class GlobalValidationHandler {

    //관심사를 분리시킴
    // PostMapping 혹운 PutMapping이 붙어있는 메서드를 실행하기 직전에 Advice를 호출하라
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void badRequestHandle(JoinPoint jp) {//jp는 실행될 실제 메서드의 모든 것을 투영하고 있다.
        Object[] args = jp.getArgs();//메서드의 매개변수들
        for (Object arg : args) { // 어노테이션은 제외하고 매개변수 개수만큼 반복
            // intansof는 타입 검증
            //errors 타입이 매개변수에 존재하고
if (arg instanceof Errors) {
    System.out.println("에러 400 처리 필요함");
    Errors errors = (Errors) arg;

    // 실제 error가 존재한다면
    if (errors.hasErrors()) { // error가 하나 이상이면 true가 뜸
        List<FieldError> fErrors = errors.getFieldErrors();

        for (FieldError fieldError : fErrors) {
            throw new Exception400(fieldError.getField()+":"+fieldError.getDefaultMessage());//getField가 필드명
        }

    }
}
        }

    }



//    @Before("@annotation(shop.mtcoding.blog._core.error.anno.MyBefore)") //intercept와 차이점 정리
//    // jp 안에 해당 method 정보가 투영되어 있음
//    public void beforeAdvice(JoinPoint jp) {
//
//        String name =jp.getSignature().getName();
//        System.out.println("Before advice: " + name);
//    }
//    @After("@annotation(shop.mtcoding.blog._core.error.anno.MyAfter)")
//    public void afterAdvice(JoinPoint jp) {
//
//        String name =jp.getSignature().getName();
//        System.out.println("After advice: " + name);}
//
//    // 앞 뒤를 다 관리하는 aroundAdvice
//    @Around("@annotation(shop.mtcoding.blog._core.error.anno.MyAround)")
//    public Object aroundAdvice(ProceedingJoinPoint jp) {
//
//        String name =jp.getSignature().getName();
//        System.out.println("Around advice 직전 : " + name);
//        try {
//            Object result = jp.proceed(); // Controller 함수가 호출됨 before와 다르게 직접 호출 가능
//            System.out.println("Around advice 직후 : " + name);
//            System.out.println("result 값 : "+ result);
//            return result;
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//    }


}
