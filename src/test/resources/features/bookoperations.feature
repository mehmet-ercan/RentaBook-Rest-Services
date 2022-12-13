Feature: Kitap işlemleri

  Scenario: Kitap listeleme başarılı senaryo
    Given Kitaplar sisteme eklenmiş olmalı
    When Kullanıcı kitapları listelemek istediğinde
    Then Dükkanda bulunan kitapları listeler

  Scenario: Kitap ekleme başarılı senaryosu
    Given Kullanıcı kitap bilgilerini girer
    When Kullanıcı kitap ekleme butonuna tıkladığında
    Then Kitap eklenmiş olur

  Scenario: Satın alma ve kiralama yapılırken kitapların sepete başarılı olarak eklenmesi
    Given Kitabın isbn numarası ilgili metin kutusuna yazılır
    When Kitap isbn numarası ile beraber aratılır
    Then İlgili kitap sepete eklenir

  Scenario: Kitap ekleme başarılı senaryosu
    Given Kitap bilgileri girilmiştir
    When Kitap ekle butonuna tıklandığında
    Then Kitap eklenir

