package com.dormhub.service;

import com.dormhub.repository.KonfigurasiRepository;
import com.dormhub.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private KonfigurasiRepository konfigurasiRepository;

    public int[] assignRoom() {
        // Ambil konfigurasi
        var konfigurasi = konfigurasiRepository.findById(1).orElseThrow(
            () -> new RuntimeException("Konfigurasi tidak ditemukan")
        );

        int maxKasurPerKamar = konfigurasi.getKasur(); // Jumlah kasur per kamar
        int maxKamarPerLantai = konfigurasi.getKamar(); // Jumlah kamar per lantai
        int totalLantai = konfigurasi.getLantai(); // Jumlah lantai

        // Validasi konfigurasi
        validateKonfigurasi(maxKasurPerKamar, maxKamarPerLantai, totalLantai);

        // Cari kamar terakhir yang digunakan
        int lastRoomNumber = mahasiswaRepository.findLastRoomNumber();
        int lastFloor = getFloorFromRoomNumber(lastRoomNumber); // Lantai dari kamar terakhir
        int occupantsInLastRoom = mahasiswaRepository.countOccupantsInRoom(lastRoomNumber);

        // Tentukan lantai, kamar, dan kasur
        return determineRoomAndBed(
            lastRoomNumber, lastFloor, occupantsInLastRoom,
            maxKasurPerKamar, maxKamarPerLantai, totalLantai
        );
    }

    /**
     * Validasi nilai konfigurasi.
     */
    private void validateKonfigurasi(int kasur, int kamar, int lantai) {
        if (kasur <= 0) {
            throw new IllegalArgumentException("Jumlah kasur per kamar harus lebih besar dari 0");
        }
        if (kamar <= 0) {
            throw new IllegalArgumentException("Jumlah kamar per lantai harus lebih besar dari 0");
        }
        if (lantai <= 0) {
            throw new IllegalArgumentException("Jumlah lantai harus lebih besar dari 0");
        }
    }

    /**
     * Hitung lantai berdasarkan nomor kamar.
     */
    private int getFloorFromRoomNumber(int roomNumber) {
        return roomNumber / 100; // Contoh: kamar 201 -> lantai 2
    }

    /**
     * Tentukan kamar dan kasur berdasarkan konfigurasi.
     */
    private int[] determineRoomAndBed(
        int lastRoomNumber, int lastFloor, int occupantsInLastRoom,
        int maxKasurPerKamar, int maxKamarPerLantai, int totalLantai
    ) {
        if (occupantsInLastRoom >= maxKasurPerKamar) {
            // Pindah ke kamar berikutnya
            if (lastRoomNumber % 100 < maxKamarPerLantai) {
                // Masih ada kamar di lantai yang sama
                return new int[]{lastRoomNumber + 1, 1}; // Kasur pertama di kamar baru
            } else if (lastFloor < totalLantai) {
                // Pindah ke lantai berikutnya
                int nextFloorRoom = (lastFloor + 1) * 100 + 1; // Kamar pertama di lantai baru
                return new int[]{nextFloorRoom, 1}; // Kasur pertama
            } else {
                throw new RuntimeException("Semua kamar di semua lantai penuh");
            }
        } else {
            // Tambahkan penghuni ke kasur berikutnya di kamar yang sama
            return new int[]{lastRoomNumber, occupantsInLastRoom + 1};
        }
    }
}
