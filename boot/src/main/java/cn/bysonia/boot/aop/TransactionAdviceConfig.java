package cn.bysonia.boot.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

//@Aspect
//@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution (* cn.bysonia.boot.service.*.*(..))";

//    @Autowired
    private PlatformTransactionManager transactionManager;//可以开始mybatis的transactionManager, 也可以是 hibernate 的transactionManager

//    @Bean
    public TransactionInterceptor txAdvice(){
        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED.rollbackOn(new Throwable());
        txAttr_REQUIRED.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

        DefaultTransactionAttribute txAttr_READONLY = new DefaultTransactionAttribute();
        txAttr_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source =  new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", txAttr_REQUIRED);
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);

        source.addTransactionalMethod("get*", txAttr_READONLY);
        source.addTransactionalMethod("query*", txAttr_READONLY);
        source.addTransactionalMethod("find*", txAttr_READONLY);
        source.addTransactionalMethod("list*", txAttr_READONLY);
        source.addTransactionalMethod("count*", txAttr_READONLY);
        source.addTransactionalMethod("is*", txAttr_READONLY);
        source.addTransactionalMethod("if*", txAttr_READONLY);

        return new TransactionInterceptor(transactionManager, source);
    }

    public Advisor txAdviceAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return  new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
