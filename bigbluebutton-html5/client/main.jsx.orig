import React from 'react';
import { Meteor } from 'meteor/meteor';
import { render } from 'react-dom';
import { renderRoutes } from '/imports/startup/client/routes';

Meteor.startup(() => {
<<<<<<< HEAD

  loadLib('sip.js');
  loadLib('bbb_webrtc_bridge_sip.js');
  loadLib('bbblogger.js');
  loadLib('jquery.json-2.4.min.js');
  loadLib('jquery.FSRTC.js');
  loadLib('jquery.verto.js');
  loadLib('verto_extension.js');
  loadLib('jquery.jsonrpcclient.js');

  loadUserSettings();

  let browserLanguage = navigator.language; //set this manually to force localization in a specific language
//  let request = new Request(`${window.location.origin}/html5client/locale?locale=${browserLanguage}`);
var fetchUrl = `${window.location.origin}/html5client/locale?locale=en`;

  fetch(fetchUrl, { method: 'GET' })
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      setMessages(data);
    })
    .catch(function error(err) {
      console.log('request failed', err);
    });

=======
  render(renderRoutes(), document.getElementById('app'));
>>>>>>> upstream/master
});

