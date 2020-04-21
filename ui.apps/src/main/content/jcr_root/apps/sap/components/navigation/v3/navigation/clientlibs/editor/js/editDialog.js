
(function (window, $, channel, Granite, Coral) {
    "use strict";

    var selectors = {
        dialogContent: ".sap-navigation_v3__editor",
        socials: ".cmp-navigation__editor-multifield_socials",
        copyright: ".cmp-navigation__editor-multifield_actions_copyright",
        footerlinks: ".cmp-navigation__editor-multifield_actions_footerlinks",
        infolinks: ".cmp-navigation__editor-multifield_actions_infolinks"
    };

    $(document).on("dialog-loaded", function (event) {

        var $dialog = event.dialog;

        if ($dialog.length) {
            var dialogContent = $dialog[0].querySelector(selectors.dialogContent);

            if (dialogContent) {
                var socials = dialogContent.querySelector(selectors.socials);
                var copyright = dialogContent.querySelector(selectors.copyright);
                var footerlinks = dialogContent.querySelector(selectors.footerlinks);
                //                         var infolinks = dialogContent.querySelector(selectors.infolinks);

                if (socials) {
                    Coral.commons.ready(socials, function (e) {
                        onSocialsChange(e);

                    });
                }

                if (copyright) {
                    Coral.commons.ready(copyright, function (e) {
                        onCopyrightChange(e);
                    });
                }

                if (footerlinks) {
                    Coral.commons.ready(footerlinks, function (e) {
                        onFooterlinksChange(e);
                    });
                }
            }
        }
    });

    $(document).on("change", selectors.socials, function (e) {
        onChangeAddButton(e);
    });

    $(document).on("change", selectors.copyright, function (e) {
        onChangeAddButton(e);
    });

    $(document).on("change", selectors.footerlinks, function (e) {
        onChangeAddButton(e);
    });

    function onSocialsChange(e) {
        disableButton(e);
    };

    function onCopyrightChange(e) {
        disableButton(e);
    };

    function onFooterlinksChange(e) {
        disableButton(e);
        let footers = e.items.getAll();
        jQuery.each(footers, function (index, value) {
            let element = this.querySelector(selectors.infolinks);
            disableButton(element);
        });
    };


    function onChangeAddButton(e) {
        let element = e.target;
        disableButton(element);
    };


    function disableButton(element) {

        let validationName = element.getAttribute("data-validation");
        let max = validationName.replace("multifield-max-", "");
        max = parseInt(max);

        if (element.items.length == max) {
            let childs = $('#' + element.id).children();

            jQuery.each(childs, function (index, value) {
                if (value.localName === 'button') {
                    $(value).attr('disabled', 'disabled');
                    $(value).attr('style', 'color: red');
                }
            })
        } else {
            let childs = $('#' + element.id).children();
            jQuery.each(childs, function (index, value) {
                if (value.localName === 'button') {
                    $(value).removeAttr('disabled');
                    $(value).removeAttr('style', 'color: red');
                }
            })
        }


    };


})(window, jQuery, jQuery(document), Granite, Coral);

