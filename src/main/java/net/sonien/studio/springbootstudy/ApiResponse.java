package net.sonien.studio.springbootstudy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiResponse<T> {
    public final int status;
    public final String error;
    public final T result;
}
