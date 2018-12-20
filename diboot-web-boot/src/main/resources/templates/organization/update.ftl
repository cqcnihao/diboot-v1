<#include "../include/head.ftl">
<body class="${bodyClass}">
<@wrapper>
	<@location loc1="单位管理" loc1url="${ctx.contextPath}/organization/" loc2="更新单位" />
	<div class="content">
	<@portlet>
		<@portletTitle title="更新单位" icon="fa-edit"></@portletTitle>
		<#include "_form.ftl">
	</@portlet><#-- END PAGE BODY -->
	</div>
</@wrapper>
</body>
</html>
