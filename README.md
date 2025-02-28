# Tutorial Advanced Programming

Nama: Rizqya Az Zahra Putri

NPM: 2306244936

Kelas: B

🔗 Deployment: [E-Shop](https://embarrassed-perle-rizqyazzahra-c19841c3.koyeb.app/)

---

<details>
<summary>Module 1</summary>

## Reflection 1
_Clean code principles_ dan _secure coding_ practices yang saya terapkan antara lain:
* **Pemberian nama yang bermakna**: Saya menggunakan nama yang jelas dan deskriptif untuk penamaan class, method, dan variable. Penamaan ini mempermudah untuk memahami kode. Sebagai contoh, method seperti `findProductById` dan `deleteProductPage`, serta nama kelas seperti `ProductController`, `ProductService`, atau `ProductRepository`. 
* **Menghindari pengulangan kode**: Saya mengimplementasikan method `findProductById` yang dapat digunakan baik untuk edit dan delete produk, sehingga tidak terdapat duplikasi kode 
* **Penggunaan method yang efisien**: Setiap method yang ada dipastikan memiliki satu tujuan utama, yang tercermin dari nama metodenya, seperti create, edit, dan delete
* **Penggunaan UUID**: Menggunakan UUID sebagai ID produk untuk mencegah percobaan akses berulang dan menghindari ID yang mudah diprediksi, sehingga meningkatkan keamanan sistem.

## Reflection 2
### 1. Unit Test

Setelah menulis unit test, saya merasa bahwa unit test sangat membantu dalam memastikan kode berjalan sesuai dengan yang diharapkan. Unit test dapat membantu saya dalam mendeteksi potensi kesalahan tanpa harus menjalankan berulang kali keseluruhan program secara manual.

Jumlah unit test dalam suatu class tergantung pada kompleksitas dan jumlah fungsionalitas yang ada. Idealnya, mencakup semua method yang penting dan setidaknya dengan beberapa skenario, seperti positive dan negative cases.

Untuk memastikan bahwa unit test yang dibuat sudah cukup, kita dapat menggunakan metrik _code coverage_. _Code coverage_ ini akan mengukur sejauh mana source code diuji oleh unit test. Namun, mencapai _code coverage_ 100% tidak selalu berarti bahwa kode bebas dari bug atau _error_. Unit test hanya menguji skenario yang telah diprediksi, tetapi tidak dapat sepenuhnya menjamin tidak ada bug tersembunyi atau _error_ yang muncul akibat kombinasi kondisi yang tidak diuji. Maka dari itu, tetap memerlukan praktik test lain, seperti _integration testing_, _manual testing_, dan _code review_ untuk memastikan kualitas perangkat lunak secara keseluruhan.

### 2. Functional Test

Membuat kelas functional test baru untuk verifikasi jumlah item dalam daftar produk dengan prosedur setup dan variabel instan yang sama seperti `CreateProductFunctionalTest.java` dapat berdampak pada kebersihan kode karena berpotensi menyebabkan duplikasi kode, yang membuatnya lebih sulit untuk dipelihara dan diperbarui. Duplikasi ini melanggar prinsip DRY (Don't Repeat Yourself) karena duplikasi kode yang fungsinya sama. Selain itu, jika ada banyak kelas pengujian dengan logika setup yang sama, maka perubahan di satu tempat harus diperbarui di banyak lokasi secara manual, meningkatkan risiko tidak konsisten.

Untuk menjaga kebersihan kode, ada beberapa pendekatan yang dapat diterapkan. Salah satunya adalah dengan membuat _base class_ yang menangani setup umum untuk semua functional test suite, sehingga kelas pengujian lain dapat mewarisinya dan menghindari duplikasi kode. Selain itu, memanfaatkan anotasi JUnit seperti `@BeforeEach` atau `@BeforeAll` dapat membantu dalam menginisialisasi variabel atau melakukan langkah persiapan sebelum setiap pengujian dijalankan. Terakhir, logika yang sering digunakan sebaiknya dipisahkan ke dalam utility class agar dapat dipanggil kembali tanpa perlu menyalin kode ke berbagai tempat. Dengan menerapkan langkah-langkah ini, kebersihan kode dapat terjaga, meningkatkan kemudahan untuk dibaca, serta proses pemeliharaan dan pengembangan menjadi lebih efisien.

</details>

<details>
<summary>Module 2</summary>

## Reflection

1. **Code Quality Issues and Fixing Strategies**
   * **Unused local variable**
     Pada `ProductRepositoryTest.java`, terdapat variabel `firstEdit` yang dideklarasikan tetapi tidak digunakan. Oleh karena itu, saya menghapus variabel tersebut untuk meningkatkan keterbacaan kode dan mengurangi potensi kebingungan.
   * **Empty method tanpa implementasi**
     - Method `setUp()` pada `ProductRepositoryTest.java` awalnya kosong tanpa komentar atau implementasi, sehingga bisa membingungkan. Saya memperbaikinya dengan menambahkan komentar yang menjelaskan bahwa method ini disiapkan untuk pengaturan awal jika diperlukan nantinya.
     - Method `contextLoads` pada `EshopApplicationTests.java` yang memang sengaja kosong. Saya menambahkan komentar yang menjelaskan bahwa metode ini dibiarkan kosong karena tujuan utamanya hanya untuk memastikan konteks aplikasi dapat dimuat tanpa kesalahan.

2. **Continuous Integration and Continuous Deployment**
   
   Menurut saya, implementasi yang saya lakukan ini telah memenuhi prinsip _Continuous Integration_ dan _Continuous Deployment_. CI terpenuhi dengan saya menggunakan Github Actions untuk menjalankan beberapa _workflow_, seperti `ci.yml`, `sonarcloud.yml`, dan `scorecard.yml`. `ci.yml` secara otomatis akan menjalankan seluruh _test_ yang sudah saya buat setiap kali ada perubahan kode (_push_ atau _pull request_), `scorecard.yml` akan menjalankan OpenSSF Scorecard untuk mengevaluasi keamanan, dan `sonarcloud.yml` yang akan menganalisis kualitas kode dengan SonarCloud. Sementara untuk CD, diterapkan melalui PaaS Koyeb. Setiap setelah perubahan kode melewati tahap CI, akan langsung di-_deploy_ tanpa langkah manual tambahan.
</details>

<details>
<summary>Module 3</summary>

## Reflection

1. **Principles Applied to the Project**
    * Single Responsibility Principle (SRP)

      Saya memisahkan `ProductController` dan `CarController` agar masing-masing class hanya memiliki satu tanggung jawab.
    * Open/Closed Principle (OCP)

      Sudah memenuhi OCP karena apabila kedepannya terdapat implementasi baru, dapat dilakukan tanpa perlu mengubah kode yang sudah ada yang bergantung pada interface.
    * Liskov Substitution Principle (LSP)

      Pada before-solid, `CarController` mewarisi `ProductController` padahal memiliki perilaku yang berbeda. Hal ini melanggar LSP karena objek subclass (`CarController`) tidak bisa menggantikan superclass-nya (`ProductController`) tanpa mengubah perilakunya. Maka dari itu, saya memperbaikinya dengan menghapus _extends_ `ProductController` pada `CarController`.
    * Interface Segregation Principle (ISP)

      Sudah diterapkan pada interface yang ada, yaitu `CarService` dan `ProductService` karena keduanya  hanya berisi _method_ yang relevan dengan masing-masing model.
    * Dependency Inversion Principle (DIP)

      Sebelumnya, `CarController` bergantung langsung pada implementasi konkret (`CarServiceImpl`). Hal ini melanggar prinsip DI karena high-level class (`CarController`) tidak seharusnya bergantung pada low-level class(`CarServiceImpl`). Saya memperbaikinya dengan mengganti _data type_ `carService`menjadi `CarService` yang merupakan interface.

2. **Advantages of Applying SOLID**

   Menerapkan prinsip SOLID memberikan banyak keuntungan, yang dapat meningkatkan kualitas, fleksibilitas, dan pemeliharaan kode dalam proyek. Misalnya, dengan menerapkan SRP, setiap kelas hanya memiliki satu tugas utama sehingga kode lebih terorganisir dan lebih mudah dipahami. Dengan OCP, kita dapat menambah fungsionalitas sistem tanpa mengubah kode yang ada. Lalu, penerapan LSP memungkinkan kita mengganti superclass dengan subclass tanpa menyebabkan error. ISP memisahkan antarmuka sesuai kebutuhan klien, menghindari ketergantungan pada method yang tidak diperlukan. Terakhir, DIP membuat kode kita menjadi lebih fleksibel terhadap perubahan. Contoh penerapannya telah dijelaskan pada nomor 1.


3. **Disadvantages of Not Applying SOLID**

   Tidak menerapkan prinsip SOLID dapat menyebabkan kode mejadi sulit dipahami, diperbaiki, dan dikembangkan. Tanpa SRP, kelas akan memiliki banyak tanggung jawab, membuat kode lebih kompleks dan sulit dipahami serta mempersulit pemeliharaan. Tanpa OCP, menambahkan fungsionalitas baru akan memerlukan perubahan langsung pada kode yang ada, hal ini dapat meningkatkan risiko bug. LSP yang tidak diterapkan bisa mengakibatkan kesulitan dalam mengganti kelas turunan tanpa merusak fungsionalitas, karena kelas turunan mungkin tidak memenuhi kontrak yang dibutuhkan. Sebagai contoh, jika `CarController` menjadi subclass dari `ProductController`, `CarController` akan mewarisi method yang sebenarnya tidak dibutuhkan. Hal ini dapat meningkatkan risiko error. Tanpa ISP, kode menjadi tidak efisien karena mengimplementasikan method yang tidak diperlukan. Terakhir, tanpa DIP, ketergantungan pada implementasi konkret akan membuat kode lebih sulit diganti dan diuji, karena penggantian ketergantungan atau pengujian unit akan menjadi lebih rumit.
</details>