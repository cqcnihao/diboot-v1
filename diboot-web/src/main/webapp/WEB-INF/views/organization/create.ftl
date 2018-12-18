<#assign className = 'organization' />
<#include "../include/head.ftl">
<body class="${bodyClass}">
<@wrapper>
    <@location loc1="单位管理" loc1url="${ctx.contextPath}/organization/" loc2="新建单位" back=true/>
    <@portlet>
        <@portletTitle title="新建单位" icon="fa-plus">
            <div class="tools">
                <a href="javascript:" class="collapse"> </a>
            </div>
        </@portletTitle>
        <#include "_form.ftl">
    </@portlet><#-- END PAGE BODY -->
</@wrapper>
</body>
</html>