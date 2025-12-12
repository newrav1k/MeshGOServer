package ru.mirea.newrav1k.authservice.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.newrav1k.authservice.repository.TrackerRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrackerService implements UserDetailsService {

    private final TrackerRepository trackerRepository;

    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        log.debug("Loading tracker by username: username={}", username);
        return this.trackerRepository.findByUsername(username)
                .map(tracker -> {
                    log.debug("Found tracker by username: username={}", tracker.getUsername());
                    return User.builder()
                            .username(tracker.getUsername())
                            .password(tracker.getPassword())
                            .authorities(new SimpleGrantedAuthority(tracker.getAuthority().name()))
                            .build();
                })
                .orElseThrow(() -> {
                    log.warn("Tracker with username={} not found", username);
                    return new UsernameNotFoundException("Tracker with username \"" + username + "\" not found");
                });
    }

}