from rest_framework_json_api import serializers
from .models import Koyla, Card, Intro, GrammarCard, Alphabet


class GrammarCardSerializer(serializers.ModelSerializer):
	id = serializers.ReadOnlyField()
	class Meta:
		model = GrammarCard
		fields = ['id', 'title', 'body', 'comment', 'category']

class KoylaSerializer(serializers.ModelSerializer):
	id = serializers.ReadOnlyField()
	grammarCard = GrammarCardSerializer(read_only=True)
	class Meta:
		model = Koyla
		fields = [ 'id','word','la','comment','grammarCard' ]

class CardSerializer(serializers.ModelSerializer):
	id = serializers.ReadOnlyField()
	grammarCard = GrammarCardSerializer(read_only=True)
	class Meta:
		model = Card
		fields = [ 'id','front','back','flip','grammarCard' ]

class IntroSerializer(serializers.ModelSerializer):
	id = serializers.ReadOnlyField()
	class Meta:
		model = Intro
		fields = [ 'id','title','body' ]

class AlphabetSerializer(serializers.ModelSerializer):
	id = serializers.ReadOnlyField()
	class Meta:
		model = Alphabet
		fields = [ 'id','letter','name','example' ]
