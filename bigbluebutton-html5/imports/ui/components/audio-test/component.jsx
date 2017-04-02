import React from 'react';
import Button from '/imports/ui/components/button/component';
import styles from '../settings/styles.scss';
import { defineMessages, injectIntl } from 'react-intl';

class AudioTest extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const {
      intl,
    } = this.props;

    return (
        <Button className={styles.testAudioBtn}
          label={intl.formatMessage(intlMessages.playSoundLabel)}
          icon={'unmute'}
          size={'md'}
          color={'primary'}
          onClick={this.props.handlePlayAudioSample}
        />
    );
  }
};

const intlMessages = defineMessages({
  playSoundLabel: {
    id: 'app.audio.playSoundLabel',
  },
});

export default injectIntl(AudioTest);
