<?xml version="1.0" encoding="${cg.getCharSet()}"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN 2.0//EN" "http://www.springframework.org/dtd/spring-beans-2.0.dtd">
<beans>
      
      <!-- ${cg.getModelName()} service -->	
	  <bean id="${cg.getObjectName()}Service" class="${cg.getPackagePath()}.service.impl.${cg.getClassName()}ServiceImpl"/>
	  
</beans>