package com.validation.demo.repo;

import com.validation.demo.domain.Notice;
import com.validation.demo.dto.CreateRequest;
import com.validation.demo.dto.UpdateRequest;
import com.validation.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryRepo {

    private List<Notice> noticeList = new ArrayList<>();

    public void create(CreateRequest request){
        Notice notice = new Notice(request);
        noticeList.add(notice);
    }

    public Notice findById(Integer id){
        return noticeList.stream().filter(notice -> notice.getId().equals(id)).findFirst().orElseThrow(ResourceNotFoundException::new);
    }

    public void update(Integer id ,UpdateRequest request){
        Notice byId = findById(id);
        byId.update(request);
    }
}
