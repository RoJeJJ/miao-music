package com.roje.miao.music.block;

public interface ContractView<T> {
    void success(T data);
    void fail(Throwable e);
}
