package com.example.aspect;

import com.example.service.ActivityLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class ActivityLogAspect {

    @Autowired
    private ActivityLogService activityLogService;

    @AfterReturning(pointcut = "execution(* com.example.controller.*Controller.save*(..)) || " +
                             "execution(* com.example.controller.*Controller.delete*(..)) || " +
                             "execution(* com.example.controller.*Controller.update*(..)) || " +
                             "execution(* com.example.controller.*Controller.approve*(..)) || " +
                             "execution(* com.example.controller.*Controller.expire*(..))",
                    returning = "result")
    public void logActivity(JoinPoint joinPoint, Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpSession session = attributes.getRequest().getSession(false);
            if (session != null && session.getAttribute("username") != null) {
                String username = (String) session.getAttribute("username");
                String methodName = joinPoint.getSignature().getName();
                String controllerName = joinPoint.getTarget().getClass().getSimpleName().replace("Controller", "").toLowerCase();
                String action;
                String target = controllerName;
                Long targetId = null;

                // Xác định hành động
                if (methodName.contains("save")) {
                    action = "Thêm";
                } else if (methodName.contains("delete")) {
                    action = "Xóa";
                } else if (methodName.contains("update")) {
                    action = "Sửa";
                } else {
                    return; // Bỏ qua nếu không phải hành động cần ghi
                }

                // Lấy targetId từ tham số hoặc kết quả
                Object[] args = joinPoint.getArgs();
                for (Object arg : args) {
                    if (arg instanceof Long) {
                        targetId = (Long) arg;
                        break;
                    }
                }

                // Ghi nhật ký
                if (targetId != null) {
                    activityLogService.saveActivityLog(username, action, target, targetId);
                }
            }
        }
    }
}