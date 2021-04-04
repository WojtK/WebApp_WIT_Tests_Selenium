Feature: Scenariusz WIT_27 - [Aplikacja]: Wylogowanie
  Wylogowanie z mojego konta
  Jako uzytkownik chce móc wylogować sie z mojego konta
  aby nikt nie był w stanie z niego skorzystać nie znając hasła i loginu

  Scenario Outline: Logowanie sie za pomocą poprawnych danych
    Given  Uzytkownik wchodzi na strone o adresie "<url>"
    When  Uzytkownik wpisuje prawidlowy login "<username>" haslo "<password>" i klika "loguj"
    Then  Uzytkownik jest zalogowany

    Examples:
      | username | password | url                                                 |
      | test     | test     | http://127.0.0.1:8000/account/login/?next=/account/ |
      | test     | test     | http://127.0.0.1:8000/account/login/?next=/account/ |


  Scenario Outline: Logout
    Given Uzytkownik wchodzi na strone o adresie "<url>"
    When Uzytkownik wpisuje prawidlowy login "<username>" haslo "<password>" i klika "loguj"
    Then Uzytkownik jest zalogowany
    Then Uzytkownik wylogowuje sie
    Examples:
      | username | password | url                                                 |
      | test     | test     | http://127.0.0.1:8000/account/login/?next=/account/ |
      | test     | test     | http://127.0.0.1:8000/account/login/?next=/account/ |