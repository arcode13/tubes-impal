package com.dormhub.service;

import com.dormhub.model.Jurusan;
import com.dormhub.model.Level;
import com.dormhub.model.Mahasiswa;
import com.dormhub.model.User;
import com.dormhub.repository.MahasiswaRepository;
import com.dormhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private RoomService roomService; // Tambahan RoomService untuk logika lantai, kamar, dan kasur

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Mendaftarkan pengguna baru
     *
     * @param user Data user dari form pendaftaran
     * @return Pesan sukses atau error
     */
    public String registerUser(User user, Jurusan jurusan) {
        // Validasi email sudah digunakan
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email sudah terdaftar";
        }
    
        try {
            // Set waktu saat ini
            LocalDateTime now = LocalDateTime.now();
            
            // Hash password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
    
            // Atur level default untuk Mahasiswa
            Level level = new Level();
            level.setId(1); // ID untuk Mahasiswa
            user.setLevel(level);
            
            // Atur waktu pembuatan dan pembaruan
            user.setCreatedAt(now);
            user.setUpdatedAt(now);
    
            // Simpan user ke database
            User savedUser = userRepository.save(user);
    
            // Simpan data mahasiswa dengan jurusan
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setUserId(savedUser.getId());
            mahasiswa.setJurusanId(jurusan.getId()); // Hubungkan dengan jurusan
            mahasiswa.setNoKamar(roomService.assignRoom()[0]);
            mahasiswa.setNoKasur(roomService.assignRoom()[1]);
            mahasiswaRepository.save(mahasiswa);
    
            return "Berhasil mendaftar";
        } catch (Exception e) {
            e.printStackTrace();
            return "Terjadi kesalahan saat mendaftar";
        }
    }    

    /**
     * Autentikasi pengguna berdasarkan email dan password
     *
     * @param email Email pengguna
     * @param password Password pengguna
     * @return User object jika autentikasi berhasil, null jika gagal
     */
    public User authenticate(String email, String password) {
        // Cari user berdasarkan email
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Periksa apakah password cocok menggunakan BCrypt
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user; // Return user jika password cocok
            }
        }

        return null; // Return null jika tidak cocok
    }
}
