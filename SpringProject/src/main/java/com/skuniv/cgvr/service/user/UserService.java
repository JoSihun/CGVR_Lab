package com.skuniv.cgvr.service.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.dto.user.UserListResponseDto;
import com.skuniv.cgvr.dto.user.UserSaveRequestDto;
import com.skuniv.cgvr.dto.user.UserUpdateRequestDto;
import com.skuniv.cgvr.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    /* 관리자 등록 */
    @Transactional
    public Long grant(final UserSaveRequestDto requestDto) {
//        User findUser = this.userRepository.findByUserId(requestDto.getUserId());
//        if (findUser != null)
//            requestDto.setUserId(null);
        return this.userRepository.save(requestDto.toEntity()).getId();
    }
    /* 관리자 수정 */
    @Transactional
    public Long update(final Long id, final UserUpdateRequestDto requestDto) {
        User entity = this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 관리자입니다. id=" + id));
        entity.update(requestDto.getUserId(), requestDto.getKorName(), requestDto.getEmail(),
                requestDto.getContact());
        return id;
    }
    /* 관리자 제거 */
    @Transactional
    public Long revoke(final Long id) {
        User entity = this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("이미 존재하지 않는 관리자입니다. id=" + id));
        this.userRepository.delete(entity);
        return id;
    }
}
