<#include "../../include/head.ftl">
<body class="${bodyClass}">
<@wrapper>
    <@location loc1="消息模板管理" loc1url="${ctx.contextPath}/msg/messageTmpl/" loc2="新建消息模板" back=true/>
    <div class="content">
        <@portlet>
            <@portletTitle title="新建消息模板">
                <div class="tools">
                    <a href="javascript:;" class="collapse"> </a>
                </div>
            </@portletTitle>
            <@portletBody>
                <#include "_form.ftl">
            </@portletBody>
        </@portlet><#-- END PAGE BODY -->
    </div>
</@wrapper>
<script type="text/javascript" src="${ctx.contextPath}/static/plugin/vue.js"></script>
<script type="text/javascript" src="${ctx.contextPath}/static/js/views/selectTmplVarible.js"></script>
</body>
</html>