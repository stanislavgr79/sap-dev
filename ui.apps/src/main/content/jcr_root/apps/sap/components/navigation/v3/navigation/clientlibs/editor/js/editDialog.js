

(function(window, $, channel, Granite, Coral) {
    "use strict";

    // class of the edit dialog content
    var CLASS_EDIT_DIALOG = ".sap-navigation_v3__editor";
    var SELECTOR_ELEMENT_NAMES = "[data-granite-coral-multifield-name='./copyrightNames']";
    console.log('SELECTOR_ELEMENT_NAMES :' + SELECTOR_ELEMENT_NAMES);
    console.log('SELECTOR_ELEMENT_NAMES size :' + SELECTOR_ELEMENT_NAMES.length);

    var SELECTOR_ELEMENT_NAMES_ADD = SELECTOR_ELEMENT_NAMES + " > [is=coral-button]";
    console.log('SELECTOR_ELEMENT_NAMES add :' + SELECTOR_ELEMENT_NAMES_ADD);

    $(document).on("change coral-multifield-item-content > input", ".cmp-navigation__editor-multifield_actions_copyright", function(event) {
           if(this.items.length == 8) {
                     var childs = $('#' + this.id).children();
                                        console.log('size :' + childs.length);
                                        jQuery.each(childs, function(index, value) {
                                            if(value.localName === 'button'){
                                                  $(value).attr('disabled', 'disabled');
                                                  $(value).attr('style', 'color: red');
                                             }
                                        })
                                    } else {
                                        var childs = $('#' + this.id).children();
                                             jQuery.each(childs, function(index, value) {
                                                   if(value.localName === 'button'){
                                                       $(value).removeAttr('disabled');
                                                       $(value).removeAttr('style', 'color: red');
                                                    }
                                             })
                                    }
    });

     $(document).on("change coral-multifield-item-content > input", ".cmp-navigation__editor-multifield_actions_footerlinks", function(event) {
              if(this.items.length == 4) {

                             var childs = $('#' + this.id).children();
                             console.log('size :' + childs.length);
                             jQuery.each(childs, function(index, value) {
                                 if(value.localName === 'button'){
                                       $(value).attr('disabled', 'disabled');
                                       $(value).attr('style', 'color: red');
                                  }
                             })
                         } else {
                             var childs = $('#' + this.id).children();
                                  jQuery.each(childs, function(index, value) {
                                        if(value.localName === 'button'){
                                            $(value).removeAttr('disabled');
                                            $(value).removeAttr('style', 'color: red');
                                         }
                                  })
                         }
     });

     $(document).on("change coral-multifield-item-content > input", ".cmp-navigation__editor-multifield_actions_infolinks", function(event) {
     console.log('size=' + this.items.length + ', id=' + this.id);

           if(this.items.length == 8) {

               var childs = $('#' + this.id).children();
               console.log('size :' + childs.length);
               jQuery.each(childs, function(index, value) {
                   if(value.localName === 'button'){
                         $(value).attr('disabled', 'disabled');
                         $(value).attr('style', 'color: red');
                    }
               })
           } else {
               var childs = $('#' + this.id).children();
                    jQuery.each(childs, function(index, value) {
                          if(value.localName === 'button'){
                              $(value).removeAttr('disabled');
                              $(value).removeAttr('style', 'color: red');
                           }
                    })
           }

     });


    // ui helper
    var ui = $(window).adaptTo("foundation-ui");

    // dialog texts
    var errorDialogTitle = Granite.I18n.get("Error");
    var errorDialogMessage = Granite.I18n.get("Failed");


})(window, jQuery, jQuery(document), Granite, Coral);
