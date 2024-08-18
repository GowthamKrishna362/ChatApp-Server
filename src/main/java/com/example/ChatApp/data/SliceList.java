package com.example.ChatApp.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Slice;

import java.lang.reflect.Type;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class SliceList {
    private int currentPage;
    private int pageSize;
    private boolean hasNext;

    public SliceList(Slice<?> slice) {
        this.currentPage = slice.getNumber();
        this.pageSize = slice.getSize();
        this.hasNext = slice.hasNext();
    }
}
