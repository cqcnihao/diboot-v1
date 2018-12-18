<#assign features=["C", "R", "U", "D", "L"] />
<#include "../include/head.ftl">
<body class="${bodyClass}">
<@wrapper>
    <@location loc1="单位管理" loc1url="${ctx.contextPath}/organization/" loc2="查看单位" back=true/>
    <@portlet>
		<@portletTitle title="查看单位">
			<@actions>
                <div class="btn-group">
                    <a class="btn btn-sm green dropdown-toggle" href="javascript:;" data-toggle="dropdown"> ${btnname!" 操作 "}
                        <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu pull-right">
                    <#if features?seq_contains("U")>
                        <li>
                            <a class="font-blue" href="${ctx.contextPath}/organization/update/${model.id}">
                                <i class="fa fa-edit font-blue"></i> 修改单位
                            </a>
                        </li>
					</#if>
                    <#if features?seq_contains("D") && (!(Session.user.id)?exists || Session.user.id!=model.id)>
                        <li>
                            <a href="#" class="action-confirm font-red" title="删除单位" data-confirm="您确认要删除该单位吗？"
                               data-url="${ctx.contextPath}/organization/delete/${model.id}" data-redirect="${ctx.contextPath}/organization/" >
                                <i class="fa fa-times font-red"></i> 删除单位
                            </a>
                        </li>
					</#if>
                    <#if features?seq_contains("C")>
                        <li>
                            <a class="font-green" href="${ctx.contextPath}/organization/create">
                                <i class="fa fa-plus font-green"></i> 新建单位
                            </a>
                        </li>
					</#if>
                    </ul>
                </div>
			</@actions>
		</@portletTitle>
		<@portletBody>
			<#include "_view.ftl">
		</@portletBody>
	</@portlet><#-- END PAGE BODY -->
</@wrapper>
</body>
</html>