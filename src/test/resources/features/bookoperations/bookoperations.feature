Feature: Kitap işlemleri özelliği
  Scenario: Kullanıcı listeleme başarılı senaryo
    Given Kitaplar sisteme eklenmiş olmalı
    When Kullanıcı kitapları listelemek istediğinde
    Then Dükkanda bulunan kitapları listeler

    Scenario: Kullanıcı kitap ekler
      Given Kullanıcı kitap bilgilerini girer
      When Kullanıcı kitap ekleme butonuna tıkladığında
      Then Kitap eklenmiş olur

  Scenario: Kullanıcı kitap eklerv2
    Given Kullanıcı kitap bilgilerini girerr
    When Kullanıcı kitap ekleme butonuna tıkladığındaa
    Then Kitap eklenmiş olurr