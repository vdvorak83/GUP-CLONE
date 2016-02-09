<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- не залогиненный -->

<div class="menu">
  <div class="container">
    <div class="notLogged">
      <ul class="notLogged-ul">
        <li class="notLogged-li"><a id='go' href="#">Регистрация</a></li>
        <li class="notLogged-li"><p>&nbsp;/&nbsp;</p></li>
        <li class="notLogged-li"><a id='goo' href="#">Вход</a></li>
      </ul>

      <div id="modal_form"><!-- Сaмo oкнo -->
        <div id="tab-container" class="tab-container">
          <ul class='etabs'>
            <li class='tab'><a href="#registration">Регистрация</a></li>
            <li class='tab'><a href="#entry-form">Вход</a></li>
          </ul>

          <div id="registration">
            <%--<form class="registration-form" method="post" action="#" role="form">--%>
              <label for="registration-email">Введите логин/ E-mail</label>
              <input type="email" name="registration-email" id='registration-email' required>

              <div class="clearfix"></div>

              <label for="registration-password">Пароль</label>
              <input type="password" name="registration-password" id='registration-password' placeholder="Не менее 6 символов" required>

              <div class="clearfix"></div>

              <label for="repeat-registration-password">Повторите пароль</label>
              <input type="password" name="repeat-registration-password" id='repeat-registration-password' required>

              <div class="clearfix"></div>

              <div class="conditions">
                <label class="label-checkbox"><input type="checkbox" id="checkbox-modal" value="1" name="k"/><span></span></label>
                <label for="checkbox-modal"><a href="#">Мною были прочитаны все условия</a></label>
              </div>
              <button class="registration-submit" id="registrationBtn">Зарегистрироваться</button>
            <%--</form>--%>
          </div>

          <div id="entry-form">
            <%--<form class="contact_form" method="post" action="#" role="form">--%>
              <label for="email">Введите логин/ E-mail</label>
              <input type="email" name="email" id='email' required>
              <div class="clearfix"></div>
              <label for="password">Пароль</label>
              <input type="password" name="password" id='password' placeholder="Не менее 6 символов" required>
              <div class="clearfix"></div>
              <div class="rememberMe">
                <label class="label-checkbox"><input type="checkbox" id="checkbox-modal" value="1" name="k"/><span></span></label>
                <label for="checkbox-modal">Запомнить меня</label>
              </div>
              <button class="submit" id="loginBtn">Войти</button>
            <%--</form>--%>
            <a class="contactA" href="/restore">Забыли пароль?</a>
          </div>
        </div>
      </div>
      <div id="overlay"></div><!-- Пoдлoжкa -->
    </div>
  </div>
</div>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>

<script>

  $(document).ready(function() {
    $("#loginBtn").click(function(){
      $.ajax({
        type: 'POST',
        url: '/login?email=' + $('#email').val() + '&password=' + $('#password').val(),
        statusCode: {
          200: function () {
            window.location.href = '/prioffice';
          },
          401: function () {
            alert("Неправильный пароль! Проверьте введенные данные!")
          },
          404: function () {
            alert("Неправильный email! Проверьте введенные данные!")
          }
        }
      });
    });

    $("#registrationBtn").click(function(){
      var profile = {
        'email' : $('#registration-email').val(),
        'password' : $('#registration-password').val()
      };

      $.ajax({
        type: 'POST',
        url: '/register',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(profile),
        statusCode: {
          201: function() {
            alert('Вы зарегистрировались! Вам выслано подтверждение на почту.')
            window.location.href = '/index';
          },
          409: function() {
            alert('Пользователь с таким email уже существует!')
          }
        }
      });
    });
  });

</script>