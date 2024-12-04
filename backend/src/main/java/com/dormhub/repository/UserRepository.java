package com.dormhub.repository;

import com.dormhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {  // Ganti Integer menjadi Long untuk ID

    boolean existsByEmail(String email);  // Mengecek apakah email sudah terdaftar

    Optional<User> findByEmail(String email);  // Mencari user berdasarkan email

    // Anda bisa menambahkan metode query lainnya jika diperlukan
    // Contoh query tambahan berdasarkan level atau status lainnya
    // Optional<User> findByLevel_Id(Long levelId);
}
