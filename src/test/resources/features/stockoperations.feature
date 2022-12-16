Feature: Stok işlemleri özelliği
  Scenario: Stokları listeleme işlemi
    Given Kitap stok bilgileri sisteme eklenmiş olmalı
    When Stok bilgileri listelenmek istendiğinde
    Then Sistemde kayıtlı olan stok bilgileri gelmelidir

    Scenario: Kayıtlı olan kitaba stok ekleme senaryosu
      Given Sistemde stok eklenecek olan kitap kayıtlı olmalıdır
      When Kayıtlı kitaba ait stok girildiğinde
      Then Stok bilgisi kaydedilmiş olur

      Scenario: Belirli bir kitabın stock bilgisine ulaşma senaryosu
        Given İlgili kitabın id <3> numarası bilinmelidir
        When Kullanıcı kitabın stock bilgilerini sorguladığında
        Then ilgili kitabın stok bilgilerine ulaşılmış olur

  Scenario: Olmayan bir kitabın stock bilgisine ulaşma senaryosu
    Given Kayıtlı olmayan bir kitabın id <3> numarası olmalıdır
    When Kullanıcı kitabın stock bilgilerini sorguladığında
    Then Kitabın kayıtlı olmadığından dolayı kullanıcı hata alır