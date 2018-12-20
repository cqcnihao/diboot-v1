<#assign features=["C","R","U","D","L"] />
<#include "../include/head.ftl">
<body class="${bodyClass}">
<@wrapper>
	<@location loc1="单位管理" loc1url="${ctx.contextPath}/organization/" loc2="单位记录" />
<div class="content">
	<@portlet>
		<@portletTitle title="单位记录">
			<#if features?seq_contains("C")>
				<@actions>
					<a class="btn btn-sm blue" href="${ctx.contextPath}/organization/create">
                        <i class="fa fa-plus"></i> 新建单位
                    </a>
				</@actions>
			</#if>
		</@portletTitle>
		<@portletBody>
			<#include "list_page.ftl">
			<#include "../include/pagination.ftl">
		</@portletBody>
	</@portlet><#-- END PAGE BODY -->
</div><#-- END CONTENT BODY -->
</@wrapper>
</body>
</html>