<form method="get" action="${ctx.contextPath}/organization/">
<div class="table-responsive">
	<table class="table table-striped table-hover">
    <thead>
    <tr>
    	<th>名称</th>
    	<th>简称</th>
        <th>上级单位</th>
    	<th>电话</th>
    	<th>Email</th>
    	<th>网址</th>
		<th>创建时间</th>
    	<th class="col-operation">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <input class="form-control search-field" name="name" value="${(criteria.name)!""}">
        </td>
        <td>
            <input class="form-control search-field" name="shortName" value="${(criteria.shortName)!""}">
        </td>
        <td>
            <select name="parentId" class="form-control search-field">
                <option value="">-选择-</option>
                <#if options??>
                    <#list options as opt>
                        <option value="${opt.v}" <#if (criteria.parentId)?? && (opt.v)?? && criteria.parentId==opt.v?string>selected</#if>>${opt.k}</option>
                    </#list>
                </#if>
            </select>
        </td>
        <td>
            <input class="form-control search-field" name="telephone" value="${(criteria.telephone)!""}">
        </td>
        <td>
            <input class="form-control search-field" name="email" value="${(criteria.email)!""}">
        </td>
        <td>
            <input class="form-control search-field" name="website" value="${(criteria.website)!""}">
        </td>
        <td>
            <input class="form-control search-field datepicker" name="createTime" value="${(criteria.createTime)!""}">
        </td>
        <td>
            <button type="submit" class="btn btn-info btn-sm search-btn">查询</button>
            <a href="${ctx.contextPath}/organization/list/" class="btn btn-default btn-sm loading-animation" title="重置查询条件"><i class="fa fa-refresh"></i></a>
        </td>
    </tr>

    <#if modelList??>
	<#list modelList as model>
    <tr>
    	<td>
			<#if (model.name)??>${model.name}</#if>
	    </td>
    	<td>
			<#if (model.shortName)??>${model.shortName}</#if>
	    </td>
        <td>
			<#if options??>
				<#list options as opt>
                    <#if (model.parentId)?? && model.parentId==opt.v>
                        ${opt.k}
                    </#if>
                </#list>
            </#if>
        </td>
    	<td>
			<#if (model.telephone)??>${model.telephone}</#if>
	    </td>
        <td>
			<#if (model.email)??>${model.email}</#if>
        </td>
    	<td>
			<#if (model.website)??>${model.website}</#if>
	    </td>
        <td>
			<#if (model.createTime)??>${model.createTime?datetime}</#if>
        </td>
        <td>
			<#if features?seq_contains("R")>
				<a href="${ctx.contextPath}/organization/view/${model.id}" class="btn btn-default btn-xs" title="查看"><i class="fa fa-search-plus"></i></a>
			</#if>
      		<#if features?seq_contains("U")>
				<a href="${ctx.contextPath}/organization/update/${model.id}" class="btn btn-default btn-xs" title="更新"><i class="fa fa-edit"></i></a>
			</#if>
            <#if features?seq_contains("D")>
				<a href="#" class="action-confirm btn btn-default btn-xs" title="删除" data-confirm="您确认要删除该项吗？" data-url="${ctx.contextPath}/organization/delete/${model.id}">
                    <i class="fa fa-close"></i>
                </a>
			</#if>
        </td>
    </tr>
	</#list>
    </#if>
	</tbody>
</table>
</div>
</form>