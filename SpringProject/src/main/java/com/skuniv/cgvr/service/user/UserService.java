package com.skuniv.cgvr.service.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.dto.user.UserListResponseDto;
import com.skuniv.cgvr.dto.user.UserSaveRequestDto;
import com.skuniv.cgvr.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        User findUser = this.userRepository.findByUserId(user.getUserId());
        if (findUser != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        return this.userRepository.save(user);
    }

    @Transactional
    public List<UserListResponseDto> findAllAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "korName", "korName");
        List<User> postsList = this.userRepository.findAll(sort);
        return postsList.stream().map(UserListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void save(final UserSaveRequestDto requestDto) {
        User findUser = this.userRepository.findByUserId(requestDto.getUserId());
        if (findUser != null) {
            /*  존재하는 아이디에 대해서 어떻게 처리할지 고민중
                RestController 동일하게 적용해서 Alert 처리하고 싶음
             */
            //throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        else {
            this.userRepository.save(requestDto.toEntity());
        }
    }
}
