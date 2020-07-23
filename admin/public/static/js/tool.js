Tool = {
  /**
   * null or "" return true
   */
  isEmpty: function (obj) {
    if ((typeof obj == 'string')) {
      return !obj || obj.replace(/\s+/g, "") == ""
    } else {
      return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
    }
  },

  /**
   * NOT NULL VALIDATION
   */
  isNotEmpty: function (obj) {
    return !this.isEmpty(obj);
  },

  /**
   * LENGTH VALIDATION
   */
  isLength: function (str, min, max) {
    return $.trim(str).length >= min && $.trim(str).length <= max;
  }
}

