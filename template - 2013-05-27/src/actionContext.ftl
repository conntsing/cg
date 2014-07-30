<?xml version="1.0" encoding="${cg.getCharSet()}"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN 2.0//EN" "http://www.springframework.org/dtd/spring-beans-2.0.dtd">
<beans default-autowire="byName">
      
      <!-- ${cg.getModelName()} action -->
	  <bean id="${cg.getObjectName()}Action" class="${cg.getPackagePath()}.controller.action.${cg.getObjectName()}.${cg.getClassName()}Action" scope="prototype">
			<property name="${cg.getObjectName()}Service">
				<ref bean="${cg.getObjectName()}Service" />
			</property>
	  </bean>
	  
</beans>