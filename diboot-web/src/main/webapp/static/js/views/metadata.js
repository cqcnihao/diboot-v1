/**
 * 元数据页面scripts
 * @author mazc
 */
(function($) {
    $(document).ready(function(){
        var flag = 'new';
        var $parent = ''
        $('.btn-additem').on("click", function(){
            var name = $("#newitemname").val();
            if(!name){
                alert("请输入元数据子项名称!");
                return false;
            }
            var value = $("#newitemvalue").val();
            if(!value){
                alert("请输入元数据子项编码!");
                return false;
            }
            var wrapper = $("#itemWrapper");
            if(flag == 'new'){
                var newRow =
                    "<div class='alert alert-info'><button aria-hidden='true' data-dismiss='alert' class='close' type='button'>×</button>"+"<p class='subItem'>"+name;
                if(value){
                    newRow += " <small>("+value+")</small>" + "</p>";
                }
                else{
                    value = "";
                }
                var jsonStr = JSON.stringify({id: 0, itemName: name, itemValue: value});
                newRow += "<input type='hidden' name='items' value='" + jsonStr + "' /></div>";
                wrapper.append(newRow);
            }
            if(flag == 'update'){
                var params = JSON.parse($parent.find('input[name="items"]').val())
                if(value){
                    $parent.find('span').text(name+' ('+value+')')
                    $parent.find('input[name="items"]').val('{"id": "'+params.id+'","itemName": "'+name+'","itemValue": "'+value+'"}')
                }else {
                    $parent.find('span').text(name)
                    $parent.find('input[name="items"]').val('{"id": "'+params.id+'","itemName": "'+name+'","itemValue": ""}')
                }
                $('.btn-additem').html('添加子项').removeClass('btn-success').addClass('btn-primary');
                flag = 'new'
            }

            $("#newitemname").val("");
            $("#newitemvalue").val("");
        });
        $('.subItem').on('click', function () {
            $parent = $(this)
            var subItem = JSON.parse($(this).find('input[name="items"]').val());
            $("#newitemname").val(subItem.itemName);
            $("#newitemvalue").val(subItem.itemValue);
            $('.btn-additem').html('更新子项').removeClass('btn-primary').addClass('btn-success');
            flag = 'update'
        })
        $('.close').on('click',function () {
            $("#newitemname").val("");
            $("#newitemvalue").val("");
        })
    });
})(jQuery);