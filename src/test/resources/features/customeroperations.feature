Feature: Müşteri işlemleri özelliği
  Scenario: Müşterinin başarılı olarak sisteme eklendiği senaryo
    Given Müşteri bilgileri alınır
    When Bilgiler ilgili alanlara girildikten sonra kaydet butonuna basılır
    Then Müşteri eklenmiş olur

    Scenario: Müşteri numarası ile başarılı bir şekilde işlem yapma senaryosu
      Given Müşteri numarası bilinmelidir
      When Müşteri numarası ilgili alana girilir
      Then Kayıtlı müşteri adına işlem yapılmış olur