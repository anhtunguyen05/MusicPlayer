/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function Validator(formSelector) {
  function getParent(element, selector) {
    while (element.parentElement) {
      if (element.parentElement.matches(selector)) {
        return element.parentElement;
      }
      element = element.parentElement;
    }
  }

  var _this = this;

  var formRules = {};

  var validatorRules = {
    required: function (value) {
      return value ? undefined : "Please Enter This Field";
    },
    email: function (value) {
      var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      return regex.test(value) ? undefined : "Please Enter Email";
    },
    min: function (min) {
      return function (value) {
        return value.length >= min
          ? undefined
          : `Please Enter Minimum ${min} Element`;
      };
    },
    max: function (max) {
      return function (value) {
        return value.length <= max
          ? undefined
          : `Please Enter Maximum ${max} Element`;
      };
    },
    confirmed: function (value) {
      var value1 = document.querySelector("#password").value;
      return value == value1 ? undefined : "Please Enter Correct Password";
    },
  };

  // lay ra form element trong DOM theo 'formSelector'
  var formElement = document.querySelector(formSelector);

  // chi xu li khi co element trong DOM
  if (formElement) {
    var inputs = document.querySelectorAll("[name][rules]");

    for (var input of inputs) {
      var rules = input.getAttribute("rules").split("|");

      for (var rule of rules) {
        var ruleInfo;
        var isRuleHasValue = rule.includes(":");

        if (isRuleHasValue) {
          ruleInfo = rule.split(":");
          rule = ruleInfo[0];
        }

        var ruleFunc = validatorRules[rule];

        if (isRuleHasValue) {
          ruleFunc = ruleFunc(ruleInfo[1]);
        }

        if (Array.isArray(formRules[input.name])) {
          formRules[input.name].push(ruleFunc);
        } else {
          formRules[input.name] = [ruleFunc];
        }
      }
      // lang nge su kien der validate(blur, change, ...)
      input.onblur = handleValidate;
      input.oninput = handleClearError;
    }
    // ham thuc hien validate
    function handleValidate(event) {
      var rules = formRules[event.target.name];
      var errorMessage;

      for (var rule of rules) {
        errorMessage = rule(event.target.value);
        if (errorMessage) break;
      }

      if (errorMessage) {
        var formGroup = getParent(event.target, ".form-group");
        if (formGroup) {
          formGroup.classList.add("invalid");
          var formMessage = formGroup.querySelector(".form-message");
          if (formMessage) {
            formMessage.innerText = errorMessage;
          }
        }
      }
      return !errorMessage;
    }

    //ham clear message loi
    function handleClearError(event) {
      var formGroup = getParent(event.target, ".form-group");
      if (formGroup.classList.contains("invalid")) {
        formGroup.classList.remove("invalid");

        var formMessage = formGroup.querySelector(".form-message");
        if (formMessage) {
          formMessage.innerText = "";
        }
      }
    }
  }

  // xu li hanh vi submit form
  formElement.onsubmit = function (event) {
    event.preventDefault();

    var inputs = document.querySelectorAll("[name][rules]");
    var isValid = true;

    for (var input of inputs) {
      if (!handleValidate({ target: input })) {
        isValid = false;
      }
    }

    // khi k co loi thi submit
    if (isValid) {
      if (typeof _this.onSubmit === "function") {
        var enableInputs = formElement.querySelectorAll("[name]");
        var formValues = Array.from(enableInputs).reduce(function (
          values,
          input
        ) {
          switch (input.type) {
            case "radio":
              values[input.name] = formElement.querySelector(
                'input[name="' + input.name + '"]:checked'
              ).value;
              break;
            case "checkbox":
              if (!input.matches(":checked")) return values;
              if (!Array.isArray(values[input.name])) {
                values[input.name] = [];
              }
              values[input.name].push(input.value);
              break;
            case "file":
              values[input.name] = input.files;
              break;
            default:
              values[input.name] = input.value;
          }
          return values;
        },
        {});
        // goi lai ham onsubmit va tra ve gtri cua form
        _this.onSubmit(formValues);
      } else {
        formElement.submit();
      }
    }
  };
}

function toggleMenu(optionBtn) {
  // Đóng tất cả menu trước khi mở menu mới
  var allMenus = document.getElementsByClassName("playlist-menu");
  for (var i = 0; i < allMenus.length; i++) {
      if (allMenus[i] !== optionBtn.nextElementSibling) {
          allMenus[i].classList.remove("show");
      }
  }

  // Lấy menu của phần tử vừa bấm và bật/tắt hiển thị
  var menu = optionBtn.nextElementSibling;
  if (menu) {
      menu.classList.toggle("show");
  }
}

// Đóng menu khi bấm ra ngoài
document.onclick = function (event) {
  var isOption = event.target.classList.contains("option");
  var isMenu = event.target.classList.contains("playlist-menu");
  
  if (!isOption && !isMenu) {
      var menus = document.getElementsByClassName("playlist-menu");
      for (var i = 0; i < menus.length; i++) {
          menus[i].classList.remove("show");
      }
  }
};