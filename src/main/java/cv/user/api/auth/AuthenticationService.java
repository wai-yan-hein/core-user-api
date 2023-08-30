package cv.user.api.auth;

import cv.user.api.common.Util1;
import cv.user.api.entity.MachineInfo;
import cv.user.api.entity.Token;
import cv.user.api.entity.TokenType;
import cv.user.api.repo.MachineInfoRepo;
import cv.user.api.repo.TokenRepo;
import cv.user.api.webflux.JWTReactiveAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final MachineInfoRepo repository;
    private final TokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JWTReactiveAuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String serialNo =Util1.cleanStr(request.getSerialNo());
        var user = repository.findBySerialNo(serialNo).orElse(null);
        if (user == null) {
            return AuthenticationResponse.builder().message("Your machine need register.").build();
        }
        if (!request.getPassword().equals(Util1.getPassword())) {
            return AuthenticationResponse.builder().message("Authentication Failed.").build();
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(serialNo, request.getPassword()));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken.getAccessToken());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken.getAccessToken())
                .accessTokenExpired(jwtToken.getAccessTokenExpired())
                .refreshToken(refreshToken.getRefreshToken())
                .refreshTokenExpired(refreshToken.getRefreshTokenExpired())
                .macId(user.getMacId())
                .build();
    }

    private void saveUserToken(MachineInfo mac, String jwtToken) {
        var token = Token.builder().macId(mac.getMacId()).token(jwtToken).tokenType(TokenType.BEARER.name()).expired(false).revoked(false).build();
        tokenRepo.save(token);
    }

    private void revokeAllUserTokens(MachineInfo info) {
        var validUserTokens = tokenRepo.findAllValidTokenByMacId(info.getMacId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

}
