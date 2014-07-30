<?xml version="1.0" encoding="${cg.getCharSet()}"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN 2.0//EN" "http://www.springframework.org/dtd/spring-beans-2.0.dtd">
<beans default-autowire="byName">
      
      <!-- ${cg.getModelName()} dao -->
	  <bean id="${cg.getObjectName()}DAO" class="${cg.getPackagePath()}.dao.ibatisImpl.${cg.getClassName()}DAOImpl">
	      <property name="sqlMapClient" ref="sqlMapClient" />
	  </bean>
	  
</beans>