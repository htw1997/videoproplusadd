package com.qfedu.mapper;

import com.qfedu.entry.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminMapper {
    int selectAdminByUserNameAndPassword(Admin admin);

}
