# Hasil verifikasi Modul 2

Tanggal verifikasi: 11 Oktober 2026.

Lingkungan: Linux, Temurin JDK 17.0.20.1+1, Kotlin 2.1.20, Gradle 8.12.1, dan JUnit 4.13.2.

Perintah build yang diperiksa: `gradle :app:testDebugUnitTest :app:run :app:runBasics wrapper --gradle-version 8.12.1`.

| Suite | Test | Gagal | Error |
|---|---:|---:|---:|
| TaskValidatorTest | 13 | 0 | 0 |
| TaskFactoryTest | 9 | 0 | 0 |
| TaskQueriesTest | 15 | 0 | 0 |
| Total | 37 | 0 | 0 |

Build akhir menghasilkan BUILD SUCCESSFUL. Seluruh kode Kotlin produksi, latihan dasar, dan test berhasil dikompilasi. Program domain menghasilkan Open [Kotlin, Testing], total menit 105, dan perubahan status salinan menjadi DONE dengan status awal tetap TODO. Percobaan pembuatan judul UI ditolak karena hanya dua karakter; pesan Invalid tersebut adalah demonstrasi validasi, bukan kegagalan build.

Program KotlinBasics menghasilkan [Kotlin, Testing], 105, Testing, dan Database sebagai tugas terbuka tersingkat. Null dan blank menghasilkan Tanpa catatan.

Laporan HTML tersedia di `bukti/laporan-html/index.html`; output Gradle di `bukti/hasil-gradle.txt`; hasil XML di `bukti/hasil-xml/`.

Verifikasi dilakukan pada model domain JVM. Belum dilakukan pengujian UI, emulator, perangkat Android mahasiswa, ataupun integrasi dengan proyek Android mahasiswa. Bukti ini bukan screenshot panel Android Studio dan bukan klaim aktivitas pribadi mahasiswa.

Build awal sempat terhenti karena runtime Java belum menyediakan JDK yang terdeteksi, kemudian karena sertifikat jaringan lingkungan. Keduanya diselesaikan pada lingkungan pengujian dengan JDK lengkap dan konfigurasi kepercayaan sertifikat yang sesuai. Tidak ditambahkan pengaturan jaringan lingkungan tersebut ke proyek yang dibagikan.

Paket ini merekam empat commit lokal atas nama ChatGPT. Riwayat tersebut mencatat hasil penyusunan paket, bukan klaim bahwa mahasiswa telah melakukan siklus test-first. Remote repository dan branch dengan NIM mahasiswa perlu disiapkan pada akun mahasiswa.
