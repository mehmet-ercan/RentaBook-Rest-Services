Feature: Kitap işlemleri

  Scenario: Kitap listeleme başarılı senaryo
    Given Kitaplar sisteme eklenmiş olmalı
    When Kullanıcı kitapları listelemek istediğinde
    Then Dükkanda bulunan kitapları listeler

  Scenario: Kitap ekleme başarılı senaryosu
    Given Kullanıcı kitap bilgilerini girer
    When Kullanıcı kitap ekleme butonuna tıkladığında
    Then Kitap eklenmiş olur

  Scenario: Kitap ekleme başarılı senaryosu
    Given Kitap bilgileri girilmiştir
    When Kitap ekle butonuna tıklandığında
    Then Kitap eklenir

  Scenario: Satın alma ve kiralama yapılırken kitapların sepete başarılı olarak eklenmesi
    Given Kitabın isbn numarası biliniyor
    When Kitap isbn numarası ile beraber aratıldığında
    Then Aranan kitap dükkanda bulunur