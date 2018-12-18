<div class="table-responsive">
    <table class="table table-striped">
	<tbody>
	<tr>
	  <td class="td-label">名称</td>
	  <td>
			${(model.name)!""}
	  </td>
	</tr>
	<tr>
        <td class="td-label">简称</td>
        <td>
		${(model.shortName)!""}
        </td>
    </tr>
    <tr>
        <td class="td-label">上级单位</td>
        <td>
			<#if options??>
				<#list options as opt>
					<#if (model.parentId)?? && model.parentId==opt.v>
						${opt.k}
					</#if>
				</#list>
			</#if>
        </td>
    </tr>
	<tr>
	  <td class="td-label">Logo</td>
	  <td>
			${(model.logo)!""}
	  </td>
	</tr>
	<tr>
	  <td class="td-label">地址</td>
	  <td>
			${(model.address)!""}
	  </td>
	</tr>
    <tr>
        <td class="td-label">电话</td>
        <td>
			${(model.telephone)!""}
        </td>
    </tr>
	<tr>
	  <td class="td-label">邮箱</td>
	  <td>
			${(model.email)!""}
	  </td>
	</tr>
    <tr>
        <td class="td-label">传真</td>
        <td>
			${(model.fax)!""}
        </td>
    </tr>
    <tr>
        <td class="td-label">网址</td>
        <td>
			${(model.website)!""}
        </td>
    </tr>
	<tr>
	  <td class="td-label">备注</td>
	  <td>
			${(model.comment)!""}
	  </td>
	</tr>
	<tr>
	  <td class="td-label">是否有效</td>
	  <td>
		  ${model.active?string('是','否')}
	  </td>
	</tr>
    <tr>
        <td class="td-label">创建人</td>
        <td>
			<#if (model.createBy)??>${model.createBy}</#if>
        </td>
    </tr>
	<tr>
        <td class="td-label">创建时间</td>
        <td>
			<#if (model.createTime)??>${model.createTime?datetime}</#if>
        </td>
	</tr>
	</tbody>
</table>
</div>

