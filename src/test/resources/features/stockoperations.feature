Feature: Stok işlemleri özelliği
  Scenario: Stokları listeleme işlemi
    Given Kitap stok bilgileri sisteme eklenmiş olmalı
    When Stok bilgileri listelenmek istendiğinde
    Then Sistemde kayıtlı olan stok bilgileri gelmelidir

    Scenario: Kayıtlı olan kitaba stok ekleme senaryosu
      Given Sistemde stok eklenecek olan kitap kayıtlı olmalıdır
      When Kayıtlı kitaba ait stok girildiğinde
      Then Stok bilgisi kaydedilmiş olur