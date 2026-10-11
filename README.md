# CampusTasks

Implementasi tugas individu Kotlin Domain Model untuk CampusTasks. Paket ini memisahkan model, validasi, factory, query collection, dan local unit test. Model memakai package `com.yafi.campustasks.domain`.

## Identitas Mahasiswa

- Nama: Muhammad Syafi'ul Mubarok
- NIM: 240605110029
- Program Studi: Teknik Informatika
- Kelas: G
- Mata Kuliah: Mobile Programming
- Tugas: Kotlin Domain Model untuk CampusTasks

## Aturan domain

- Judul harus 3–80 karakter setelah `trim()`.
- Estimasi harus 5–480 menit, termasuk batas minimum dan maksimum.
- Description null tetap null; description blank menjadi null; teks lain di-trim.
- Tag blank ditolak; tag valid di-trim dan dideduplikasi. Kapitalisasi tag dipertahankan.
- Status awal adalah `TaskStatus.TODO`.
- `markInProgress()` dan `markDone()` menghasilkan salinan melalui `copy()`.
- Query tidak mengubah list masukan.
- Pencarian mencakup judul, description, dan tags, tanpa membedakan kapitalisasi. Keyword blank menghasilkan semua task.
- `groupByStatus()` hanya berisi status yang ditemukan pada data.
- ID bertipe Long. Modul tidak menetapkan aturan positif/unik, sehingga tidak ditambahkan validasi tersebut.

Factory menjadi jalur pembuatan dari input pengguna. Constructor `Task` dan `copy()` tetap publik sesuai pola data class pada modul; keduanya dapat melewati validasi. Gunakan factory untuk input mentah. Semua property menggunakan `val`, tetapi `Set` Kotlin adalah antarmuka read-only, bukan jaminan deep immutability terhadap alias mutable. Factory membentuk set baru sehingga set masukan tidak menjadi alias tags hasil.

## Struktur

```text
app/src/main/java/com/yafi/campustasks/domain/
  Task.kt
  Identifiable.kt
  TaskPriority.kt
  TaskStatus.kt
  CreateTaskResult.kt
  TaskValidator.kt
  TaskFactory.kt
  TaskQueries.kt
app/src/test/java/com/yafi/campustasks/domain/
  TaskValidatorTest.kt
  TaskFactoryTest.kt
  TaskQueriesTest.kt
app/src/main/java/com/yafi/campustasks/demo/Main.kt
latihan/KotlinBasics.kt
JAWABAN_MODUL_2.md
```

## Menjalankan paket mandiri

Paket ini adalah proyek Kotlin/JVM untuk model domain, bukan aplikasi Android dengan UI. Memerlukan JDK 17 dan internet untuk unduhan dependency pertama. Versi build dipatok pada Kotlin 2.1.20, Gradle 8.12.1, dan JUnit 4.13.2; bukan klaim versi terbaru.

Buka folder ini di Android Studio sebagai proyek Gradle, lalu tunggu sinkronisasi. Jalankan dari terminal root proyek:

Windows PowerShell:

```powershell
.\gradlew.bat :app:testDebugUnitTest
.\gradlew.bat :app:run
```

Linux/macOS:

```bash
chmod +x gradlew
./gradlew :app:testDebugUnitTest
./gradlew :app:run
```

Task `testDebugUnitTest` pada paket mandiri adalah alias untuk JUnit JVM `test`. Tidak menguji Android, emulator, UI, atau SDK. Laporan HTML terdapat di `app/build/reports/tests/test/index.html`, XML di `app/build/test-results/test/`.

## Memasang ke proyek Android yang sudah ada

1. Salin folder `domain` dari `app/src/main/java/com/yafi/campustasks/` ke lokasi package dalam proyek Android Anda.
2. Salin ketiga file test ke `app/src/test/java/com/yafi/campustasks/domain/`.
3. Jika package diubah, ubah deklarasi package pada seluruh file produksi dan test secara konsisten.
4. Pastikan `app/build.gradle.kts` proyek Android memiliki `testImplementation("junit:junit:4.13.2")` di dalam `dependencies`.
5. Jangan menimpa konfigurasi Gradle proyek Android dengan konfigurasi JVM dalam paket ini.
6. Jalankan `./gradlew :app:testDebugUnitTest` atau `.\gradlew.bat :app:testDebugUnitTest`. Pada proyek Android task ini disediakan Android Gradle Plugin.

## Praktikum A

Jalankan `./gradlew :app:runBasics` atau `.\gradlew.bat :app:runBasics`. Anda juga dapat membuka `latihan/KotlinBasics.kt` di Kotlin Playground. File berdiri sendiri dan tidak disalin bersama Main.kt ke package domain. Output awal: `[Kotlin, Testing]`, `105`, dan `Testing`. Setelah penambahan dua task, tugas terbuka tersingkat adalah `Database` dengan 20 menit.

## Bukti pengumpulan

Isi tautan repository, nama branch `assignment/kotlin-domain-240605110029`, dan hash commit terakhir dari repository Anda. Lampirkan screenshot panel test Android Studio, output Gradle atau laporan HTML, serta README ini. Riwayat pekerjaan lokal paket disertakan melalui git bundle jika tersedia; itu bukan riwayat kontribusi pribadi mahasiswa dan bukan bukti remote repository.

## Refleksi pribadi

Setelah saya mengerjakan modul ini, saya memahami bahwa Kotlin memakai val untuk nilai yang tidak diganti dan tipe nullable seperti String? untuk data yang boleh kosong. Saya juga belajar memakai data class untuk model task, enum untuk pilihan status dan prioritas, serta sealed class agar hasil pembuatan task dapat dibedakan secara jelas. Validator memeriksa aturan input sebelum factory membuat objek. Pada koleksi, filter, map, dan groupBy memiliki kegunaan berbeda untuk memilih, mengubah, dan mengelompokkan data.
Kendala yang cukup membingungkan adalah perbedaan Java yang dipakai terminal dengan Gradle JDK di Android Studio. Saya sempat mengira keduanya otomatis memakai konfigurasi yang sama, padahal pilihan JDK Gradle dapat berbeda dari Java pada terminal. Saya mengatasinya dengan memeriksa versi Java di terminal, lalu mengecek dan menyamakan Gradle JDK ke JDK 17 melalui pengaturan Android Studio. Setelah itu saya memuat ulang proyek Gradle dan menjalankan kembali task pengujian.
Hasil pengujian pada paket ini adalah 37 tes lulus dan 0 gagal. Tes tersebut membantu saya memeriksa validasi, pembuatan task, query, serta batas input. Saya masih perlu memperdalam coroutine, siklus hidup Android, ViewModel, Room, dan pengujian yang berjalan pada emulator. Saya juga perlu lebih memahami bagaimana konfigurasi Gradle dan JDK bekerja bersama pada proyek Android.

## Penggunaan AI

Saya menggunakan ChatGPT untuk membantu menyiapkan struktur proyek,
implementasi model domain, validasi, factory, query collection, dan unit
test, serta membantu mengatasi kendala konfigurasi JDK dan Gradle.

Saya menjalankan pengujian melalui Android Studio dan memeriksa laporan
HTML. Hasilnya menunjukkan 37 tes, 0 kegagalan, dan 100% berhasil.

## Referensi

- https://kotlinlang.org/docs/data-classes.html
- https://kotlinlang.org/docs/null-safety.html
- https://kotlinlang.org/docs/sealed-classes.html
- https://kotlinlang.org/docs/collections-overview.html

## Membuka riwayat Git lokal

Empat commit lokal penyusunan paket tersedia pada `bukti/source-history.bundle`, dengan author ChatGPT dan branch `assignment/kotlin-domain`. Untuk mengembalikan riwayat beserta source code, jalankan dari folder hasil ekstraksi:

```bash
git clone bukti/source-history.bundle CampusTasks-History
```

Selanjutnya buka folder CampusTasks-History. Ubah nama branch sesuai NIM, isi refleksi pribadi, dan hubungkan ke remote repository milik Anda sebelum push. Bundle tidak mencakup folder bukti; laporan pengujian tersedia terpisah dalam ZIP ini. Riwayat commit bukan bukti kontribusi pribadi mahasiswa.
