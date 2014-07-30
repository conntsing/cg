<?xml version="1.0" encoding="${cg.getCharSet()}"?>
<!DOCTYPE sqlMapConfig PUBLIC "-//iBATIS.com//DTD SQL Map Config 2.0//EN" "http://www.ibatis.com/dtd/sql-map-config-2.dtd">
<sqlMapConfig>

    <settings
            cacheModelsEnabled="true"
            enhancementEnabled="true"
            lazyLoadingEnabled="true"
            maxRequests="5120"
            maxSessions="256"
            maxTransactions="150"
            useStatementNamespaces="true"
            />
            
    <sqlMap resource="${cg.getPackageFloderPath()}/dao/ibatisImpl/xml/${cg.getClassName()}.xml" />
    
</sqlMapConfig>
