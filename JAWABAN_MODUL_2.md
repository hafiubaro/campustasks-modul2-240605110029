# Jawaban Modul 2 Kotlin untuk Mobile Development

## Praktikum A Eksperimen Kotlin Inti

Dataset awal menghasilkan:

```text
[Kotlin, Testing]
105
Testing
```

`filter { !it.done }` mempertahankan Kotlin dan Testing karena keduanya belum selesai. `map { it.title }` menghasilkan List<String>. `sumOf` menjumlahkan 45 dan 60 menjadi 105. `firstOrNull { it.minutes >= 60 }?.title` menghasilkan String?; pada dataset tersebut nilainya Testing.

File latihan menambahkan Database (note null, 20 menit, belum selesai) dan Git (15 menit, selesai). Tugas terbuka dengan estimasi tersingkat adalah Database. `minByOrNull` aman untuk input kosong dan tidak mengubah urutan list asal. `displayNote` mengubah null dan blank menjadi “Tanpa catatan”, sedangkan teks valid di-trim.

Tiga kalimat refleksi konsep: `filter` memilih elemen, sedangkan `map` mengubah representasi setiap elemen. Hasil `firstOrNull` harus diperlakukan sebagai nullable karena pencarian dapat tidak menemukan data. Fungsi pencarian tugas tersingkat harus menyaring task DONE sebelum membandingkan estimasi agar tugas selesai tidak ikut terpilih.

## Praktikum B Domain CampusTasks

Implementasi berada pada folder domain. Validator mengumpulkan seluruh pesan kesalahan; factory melakukan normalisasi dan mengembalikan Success atau Invalid. Perubahan status menggunakan copy sehingga task awal tidak berubah. Query collection memakai filter, sortedBy, map, sumOf, dan groupBy. Test mencakup judul, estimasi, normalisasi, status, generic lookup, serta input kosong.

## Code tracing bagian 22.1

Kode `values.filter { it % 2 == 1 }.map { it * 2 }.firstOrNull { it > 10 } ?: -1` dengan values `[1, 2, 3, 4, 5]`:

1. Tipe values adalah `List<Int>`.
2. Hasil filter adalah `[1, 3, 5]`.
3. Hasil map adalah `[2, 6, 10]`.
4. `firstOrNull` bertipe `Int?` karena elemen yang cocok mungkin tidak ada.
5. Hasil akhir `-1`, karena 10 tidak memenuhi syarat `> 10`; hasil pencarian null kemudian diganti operator Elvis.

## Evaluasi formatif bagian 23

1. **val dan var.** val tidak dapat di-reassign, var dapat di-reassign. Objek yang dirujuk val belum tentu immutable: `val names = mutableListOf("A")` tetap memungkinkan `names.add("B")`. Data class copy juga merupakan shallow copy, sehingga property berupa objek mutable dapat berbagi referensi.
2. **Tipe task.description?.length.** Tipe expression adalah `Int?`. Saat description null, safe call menghasilkan null; saat tidak null, hasilnya panjang String bertipe Int.
3. **Elvis dievaluasi kapan.** Sisi kanan `?:` dievaluasi hanya ketika nilai sisi kiri null. String kosong tidak otomatis memicu Elvis. Normalisasikan blank dengan `takeIf` jika ingin memperlakukannya seperti tidak ada data.
4. **map dan filter.** map mentransformasikan elemen dan umumnya mempertahankan jumlahnya. filter mempertahankan elemen yang memenuhi predikat dan dapat mengurangi jumlahnya.
5. **firstOrNull dibanding first.** firstOrNull mengembalikan null ketika tidak ditemukan. first melempar NoSuchElementException, sehingga lebih sesuai ketika keberadaan elemen sudah dijamin.
6. **Fungsi otomatis data class.** Compiler menghasilkan equals, hashCode, toString, componentN, dan copy berdasarkan property primary constructor. Implementasi eksplisit atau inheritance tertentu dapat memengaruhi fungsi yang dihasilkan.
7. **Varian baru sealed type.** when yang dipakai sebagai expression wajib exhaustive. Penambahan varian dapat membuat when tanpa else menjadi tidak lengkap dan gagal dikompilasi sampai cabang baru ditangani.
8. **Function type (Task) -> Boolean.** Fungsi menerima satu Task dan mengembalikan Boolean. Contoh: `val isOpen: (Task) -> Boolean = { it.status != TaskStatus.DONE }`.
9. **require dibanding domain result.** require cocok untuk pelanggaran kontrak programmer yang tidak semestinya terjadi, misalnya argumen internal tidak sah. Input pengguna yang dapat salah sebaiknya menghasilkan Invalid agar kegagalan dapat ditampilkan tanpa menjadikan exception sebagai alur rutin.
10. **suspend dan background thread.** suspend tidak otomatis berpindah thread. Thread/dispatcher ditentukan context coroutine dan pemindahan context yang eksplisit. Operasi blocking tetap memerlukan dispatcher yang sesuai; keyword suspend saja tidak membuat operasi tersebut nonblocking.

## Tantangan kode bagian 23.1

```kotlin
fun pendingHighTitles(tasks: List<Task>): List<String> = tasks
    .filter { it.priority == TaskPriority.HIGH && it.status != TaskStatus.DONE }
    .sortedBy { it.title.lowercase() }
    .map { it.title }
```

Fungsi mengembalikan judul HIGH yang belum DONE dalam urutan alfabet tanpa membedakan kapitalisasi. List masukan tidak diubah. Test dengan alpha UI, Testing DONE, dan Zebra Kotlin menghasilkan `[alpha UI, Zebra Kotlin]`.

## Exit ticket bagian 22.3

- Contoh konsep yang dapat dijelaskan: safe call dan Elvis melalui `description?.trim()?.takeIf { it.isNotEmpty() } ?: "Tanpa catatan"`.
- Bagian yang masih membingungkan: isi sendiri berdasarkan pemahaman Anda. Jangan menyalin pengalaman yang bukan milik Anda.
- Keputusan desain yang meningkatkan keamanan: gunakan sealed result untuk membedakan pembuatan task berhasil dan invalid, lalu tangani kedua hasil dengan when exhaustive.

## Pemetaan sepuluh test minimum

| ID modul | Test pada implementasi |
|---|---|
| T01 | validTaskIsCreatedWithTrimmedTitle |
| T02 | invalidTitleProducesInvalidResult |
| T03 | threeCharacterTitlePasses |
| T04 | eightyOneCharacterTitleFails |
| T05 | fiveMinutesPasses |
| T06 | fourHundredEightyOneMinutesFails |
| T07 | blankDescriptionBecomesNull |
| T08 | tagsAreTrimmedAndDeduplicatedWithoutMutatingInput |
| T09 | markDoneCreatesANewInstance |
| T10 | searchWithNoMatchReturnsEmptyList |

## Kerangka refleksi pribadi 150–250 kata

Susun satu atau dua paragraf yang membahas hal berikut dengan pengalaman nyata:

- Apa yang sebelumnya dipahami tentang Kotlin dan apa yang berubah setelah latihan.
- Mengapa memakai nullable description, enum, sealed result, dan copy.
- Test yang benar-benar Anda jalankan, hasilnya, serta satu kasus batas yang Anda pahami.
- Kesalahan yang benar-benar muncul dan langkah perbaikannya; jika tidak ada, jangan membuat cerita kesalahan.
- Bantuan AI yang digunakan, verifikasi yang Anda lakukan sendiri, dan bagian yang masih perlu dipelajari.

## Langkah pengumpulan di repository Anda

1. Pastikan baseline test proyek Anda lulus.
2. Buat branch `assignment/kotlin-domain-<nim>` dengan NIM yang sebenarnya.
3. Pelajari aturan dan tulis test yang belum didukung sebelum mengubah behavior; jalankan hingga terlihat gagal sesuai alasan yang diharapkan.
4. Implementasikan perubahan minimum, jalankan ulang, dan rapikan kode.
5. Simpan sedikitnya empat commit bermakna dari pekerjaan nyata. Contoh pesan: `test: cover task validation boundaries`, `feat: validate task creation`, `feat: add task queries`, `docs: record verification and reflection`.
6. Push branch, salin tautan dan hash commit terakhir, serta ambil screenshot panel test.
7. Sertakan output test atau laporan HTML dan refleksi pribadi.

Paket ini tidak membuat remote repository atau bukti kontribusi atas nama mahasiswa.
