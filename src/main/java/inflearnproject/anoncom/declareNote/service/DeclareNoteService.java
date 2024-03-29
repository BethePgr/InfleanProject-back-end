package inflearnproject.anoncom.declareNote.service;

import inflearnproject.anoncom.declareNote.dto.DeclareUserDto;
import inflearnproject.anoncom.declareNote.repository.DeclareNoteRepository;
import inflearnproject.anoncom.domain.DeclareNote;
import inflearnproject.anoncom.domain.UserEntity;
import inflearnproject.anoncom.user.exception.NoUserEntityException;
import inflearnproject.anoncom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static inflearnproject.anoncom.error.ExceptionMessage.NO_SAME_INFO_USER_MESSAGE;

@Service
@Transactional
@RequiredArgsConstructor
public class DeclareNoteService {

    private final DeclareNoteRepository declareNoteRepository;
    private final UserRepository userRepository;

    public Page<DeclareNote> showAll(Pageable pageable) {
        return declareNoteRepository.findAllWithDetails(pageable);
    }

    public void declareUser(DeclareUserDto declareUserDto) {
        String declaredNickname = declareUserDto.getNickname();
        UserEntity user = userRepository.findByNickname(declaredNickname).orElseThrow(() -> new NoUserEntityException(NO_SAME_INFO_USER_MESSAGE));
        user.blockUser();
    }
}
