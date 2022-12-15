Feature: Satis islemleri ozelligi
  Scenario: Basarili kitap satisi senaryosu
    Given Satin alinacak kitap ve musteri bilgileri girilmelidir
    When Kitap satisi butonuna basildiginda
    Then Kitap ilgili musteriye satilmis olmalidir

  Scenario: Kitap satisi iptal senaryosu
    Given Iptal islemi icin gecerli bir fis numarasi "S121212121212" vardir
    When Satinalmayi iptal icin fis numarasi girildiginde
    Then Satinalma islemi iptal edilmelidir

  Scenario: Gecerli fis numarasi ile ilgili satis bilgilerine erisme senaryosu
    Given Satinalma islemi daha onceden yapilmis olup gecerli bir fis numarasi "S121212121212" vardir
    When Fis numarasi girildigince
    Then Ilgili satinalma bilgilerine erisilir
