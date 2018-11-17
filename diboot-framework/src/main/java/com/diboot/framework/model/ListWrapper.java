package com.diboot.framework.model;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * Model的List包装类，用于接收List并绑定校验的情况
 * @author Mazc@dibo.ltd
 * @version 2018/11/8
 *
 */
public class ListWrapper<T extends BaseModel> {

    @Valid
    private final List<T> modelList;

    public ListWrapper(final T... models){
        this.modelList = Arrays.asList(models);
    }

    public ListWrapper(final List<T> modelList){
        this.modelList = modelList;
    }

    public List<T> getModelList(){
        return modelList;
    }

}